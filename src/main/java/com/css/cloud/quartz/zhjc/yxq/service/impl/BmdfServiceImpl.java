package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.alibaba.fastjson.JSON;
import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxBmdfMapper;
import com.css.cloud.quartz.zhjc.yxq.service.IBmdfService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BmdfServiceImpl implements IBmdfService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TZhjcYxBmdfMapper bmdfMapper;
    @Override
    public void getBmdfData() {
        //日期
        Date today=new Date();
        String year = getYear(today);
        String month = getMonth(today);
        List<Map<String,Object>> deptList  = bmdfMapper.getBmdfData(year,month);

        if(month.length()==1){
            month="0"+month;
        }
        String key ="yxq-dzjc-month-"+year+month;
        redisService.set(key,deptList);
        System.out.println("部门得分统计");

    }


    public String getYear(Date today){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(today);
        String year= date.substring(0,4);
        return year;
    }
    public String getMonth(Date today){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(today);
        String month = date.substring(4,6);
        if(month.startsWith("0")){
            month = date.substring(5,6);
        }
        return  month;
    }
}
