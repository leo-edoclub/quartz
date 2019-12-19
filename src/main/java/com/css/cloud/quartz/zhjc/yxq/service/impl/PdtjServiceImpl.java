package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxMypdtjMapper;
import com.css.cloud.quartz.zhjc.yxq.service.IPdtjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class PdtjServiceImpl implements IPdtjService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private TZhjcYxMypdtjMapper mypdtjMapper;

    @Override
    public void getPdsjData() {
        //按月统计
        Date today=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String date = format.format(today);

        Map res = mypdtjMapper.getPdsjData(date);

//        String[] dates={"201710","201711","201712","201801","201802","201803","201804","201805","201806","201807","201808","201809"};
        String key ="yxq-jhj-month-"+date;
//        for(int i=0;i<12;i++){
            redisService.set(key,res);
//        }
    }
}
