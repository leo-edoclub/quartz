package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.alibaba.fastjson.JSON;
import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxZzlxtjMapper;
import com.css.cloud.quartz.zhjc.yxq.service.ZzlxtjService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 证照类型统计
 * Created by wang.wei on2018/9/12
 */
@Service
public class ZzlxtjServiceImpl implements ZzlxtjService {

    @Autowired
  TZhjcYxZzlxtjMapper zzlxtjMapper;
    @Autowired
    private RedisService redisService;
    //redis key 值前缀
    private static final String PREFIX_KEY = "yxq-dzzz-new-";
    //此list用于，当页面没有这种类型数据、或者不足10个证照类型数据的时候，补齐数据。
    private    List<String> staticZzlxNames  =staticZzlxNames = Arrays.asList("身份证","使用证","营业执照","机构代码","税务证","工商证","学生证","税务证","税务证","税务证");


    /**
     * 按天向redis中注入数据
     */
    @Override
    public void insertByDay() {
        String today = DateFormatUtils.format(new Date(), "yyyyMMdd");
        //当天数据
        List<Map> zzlxList = zzlxtjMapper.selectZzlxByDay(today);
        int length = zzlxList.size();
        //循环组装redis所需数据
        List<Map> dataList = new ArrayList<>();
//        int  zzzs = 0;//证照总数
//        int jrxz = 0;//今日新增
        List<String> zzlxmcList = new ArrayList<>();//临时存放证照类型名称
        for(int i=0;i<length;i++){
            Map temMap = new HashMap();
            Map poMap = zzlxList.get(i);
            temMap.put("name",poMap.get("ZZLXMC")+"");
            temMap.put("value",poMap.get("XZS")+"");
            dataList.add(temMap);
            //临时记录证照类型，用于  如果今日新增证照不足10个 需要补足10
//            zzlxmcList.add(poMap.get("ZZLXMC")+"");
            //计算证照类型总数和今日新增数
//            zzzs+=Integer.parseInt(poMap.get("ZZS")==null?"0":poMap.get("ZZS").toString());
//            jrxz+=Integer.parseInt(poMap.get("XZS")==null?"0":poMap.get("XZS").toString());
        }
        //如果今日新增证照类型不足10个，为了美观，需要补足
//        if(length<7){
//            int length2 = 7-length;
//            //去重
////            staticZzlxNames.removeAll(zzlxmcList);
//            List<String> staticList = new ArrayList<>(staticZzlxNames);
//            staticList.removeIf(s ->{
//                return zzlxmcList.contains(s);
//            } );
            //补足
//            for (int j=0;j<length2;j++){
//                Map tMap = new HashMap();
//                tMap.put("name",staticList.get(j));
//                tMap.put("value","0");
//                dataList.add(tMap);
//            }
//        }
       Map total =  zzlxtjMapper.selectZzlxTotal(today);
        Map zzzsMap = new HashMap();
        Map jrxzMap = new HashMap();
       zzzsMap.put("zzzs", String.valueOf(total.get("ZZS")));
       jrxzMap.put("jrxz",String.valueOf(total.get("XZS")));
        dataList.add(zzzsMap);
        dataList.add(jrxzMap);
        //注入redis
        redisService.set(PREFIX_KEY +today, dataList);
        String json = JSON.toJSONString(dataList);
        System.out.println("证照类型统计");
//        System.out.println(json);
    }

}
