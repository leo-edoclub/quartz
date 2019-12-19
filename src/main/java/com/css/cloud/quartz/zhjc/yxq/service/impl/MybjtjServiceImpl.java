package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxBjtjMapper;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxMybjMapper;
import com.css.cloud.quartz.zhjc.yxq.service.IMybjtjService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class MybjtjServiceImpl implements IMybjtjService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TZhjcYxMybjMapper mybjMapper;
    @Autowired
    private TZhjcYxBjtjMapper bjtjMapper;

    /**
     * 综合运行情况折线图
     */
    @Override
    public void getBjzhqkData() {
        NumberFormat nf =  NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);        //保留到小数点后几位
        //按月统计
        Date today=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
        String date = format.format(today);
        String date1 = format1.format(today);
        String year = date.substring(0,4);
        String month = date.substring(5,7);



//        String[] tdates={"2017-10","2017-11","2017-12","2018-01","2018-02","2018-03","2018-04","2018-05","2018-06","2018-07","2018-08","2018-09"};
//        String[] dates={"201710","201711","201712","201801","201802","201803","201804","201805","201806","201807","201808","201809"};
//        for (int i=0;i<tdates.length;i++){
            String keys = "yxq-zhyx-bj-month"+ date1;

            Map sljMap = bjtjMapper.getSljByMonth(date1);
            String zslj="";
            if(sljMap!=null && sljMap.get("TOTAL")!=null &&!sljMap.get("TOTAL").toString().equals("")){
                 zslj = sljMap.get("TOTAL").toString();//受理件量
            }else{
                zslj = "0";
            }


            Map res = mybjMapper.getBjzhzsData(date);
            String tqbjl= "";
            if(res!=null && res.get("TQBJL")!=null && !res.get("TQBJL").toString().equals("") ){
                tqbjl= res.get("TQBJL").toString();//提前办结量
            }else{
                tqbjl = "0";
            }
            String zbjl="";//总办结量
            if(res!=null && res.get("ZBJL")!=null && !res.get("ZBJL").toString().equals("")){
                zbjl= res.get("ZBJL").toString();//总办结量
            }else {
                zbjl = "0";
            }
            if(zslj!=null && !zslj.equals("0")&&zbjl!=null&&!zbjl.equals("0")){
                res.put("TQBJL",nf.format(100*Float.valueOf(tqbjl)/Float.valueOf(zbjl)));//提前办结率
                res.put("ZBJL",nf.format(100*Float.valueOf(zbjl)/Float.valueOf(zslj)));//办结率
            }else {
                res.put("TQBJL",0);//提前办结率
                res.put("ZBJL",0);//办结率
            }
            redisService.set(keys,res);

//        }
//        redisService.set(key,res);
        System.out.println("办件综合情况统计");

    }

    /**
     *平均办件时间
     */
    @Override
    public void getBjsjData() {
        NumberFormat nf =  NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);        //保留到小数点后2位
        //按月统计
        Date today=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
        String date = format.format(today);
        String date1 = format1.format(today);
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);

//        String[] tdates={"2017-10","2017-11","2017-12","2018-01","2018-02","2018-03","2018-04","2018-05","2018-06","2018-07","2018-08","2018-09"};
//        String[] dates={"201710","201711","201712","201801","201802","201803","201804","201805","201806","201807","201808","201809"};
//        for(int i=0;i<12;i++) {
            Map res = mybjMapper.getBjsjData(date);

            String keys = "yxq-bj-month" + date1;
            String formatStr = nf.format(Float.parseFloat(res.get("BJSC").toString()));
            res.put("BJSC",formatStr);
            redisService.set(keys, res);
            System.out.println("办件时间统计");
//        }
//        }
    }
}
