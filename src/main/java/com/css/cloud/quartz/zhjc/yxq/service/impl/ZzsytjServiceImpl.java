package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxZzsytjMapper;
import com.css.cloud.quartz.zhjc.yxq.service.ZzsytjService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 证照使用情况servi
 * Created by wang.wei on2018/9/13
 */
@Service
public class ZzsytjServiceImpl implements ZzsytjService {
    @Autowired
    TZhjcYxZzsytjMapper yxZzsytjMapper;
    @Autowired
    private RedisService redisService;

    //红黄牌redis key 的前缀
    private static final String PREFIX_KEY = "yxq-dzzz-use-month-";
    /**
     * 入redis
     */
    @Override
    public void insertByMouth() {
        String month = DateFormatUtils.format(new Date(),"yyyyMM");
        //查询本月数据
        List<Map> dataList = yxZzsytjMapper.selectZzsyByMouth(month);
        redisService.set(PREFIX_KEY+month,dataList);
        System.out.println("证照使用统计");
    }
}
