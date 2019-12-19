package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxRdsxMapper;
import com.css.cloud.quartz.zhjc.yxq.service.RdsxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 热点事项service
 * Created by wang.wei on2018/9/11
 */
@Slf4j
@Service
@Async
public class RdsxServiceImpl implements RdsxService {

    @Autowired
    private TZhjcYxRdsxMapper tZhjcYxRdsxMapper;
    @Autowired
    private RedisService redisService;
    //redis key 值前缀
    private static final String PREFIX_KEY = "yxq-rdsx-month-";

    /**
     * 热点事项入redis 之初始化
     */
    @Override
    public void init() {
        String[] dateStr = {"201801", "201802", "201803", "201804", "201805", "201806", "201807", "201808", "201809", "201810", "201811", "201812",};
        //获取所有月份的数据
        List<Map> rdsxList = tZhjcYxRdsxMapper.init();
        for (int i = 0; i < dateStr.length; i++) {
            List<String> oneMonthData = new ArrayList<>();
            String tempYf = dateStr[i];
            for (Map map : rdsxList) {
                String yf = map.get("YF") + "";
                if (tempYf.equals(yf)) {
                    oneMonthData.add(map.get("SXMC") + "");
                }
            }
            if (oneMonthData.size() != 0) {//将此月份的统计数据存入redis
                redisService.set(PREFIX_KEY + tempYf, oneMonthData);
            }
            if (oneMonthData.size() != 0) {//将此月份的统计数据存入redis
                List<String> sss = redisService.get(PREFIX_KEY + tempYf);
                for (String s : sss) {
                    System.out.println(s);
                }
            }
        }
    }

    /**
     * 根据月份向redis中注入数据
     */
    @Override
    public void insertByMonth() {
        //20180901
        String mouth = DateFormatUtils.format(new Date(), "yyyyMM");
        //当月数据
        List<Map> rdsxList = tZhjcYxRdsxMapper.selectRdsxByMouth(mouth);
        List<String> oneMonthData = new ArrayList<>();
        for (Map map : rdsxList) {
            oneMonthData.add(map.get("SXMC") + "");
        }
        //注入redis
        redisService.set(PREFIX_KEY + mouth, oneMonthData);
        System.out.println("热点事项统计");
    }
}
