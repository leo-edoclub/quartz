package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxBjtjMapper;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxWbsdMapper;
import com.css.cloud.quartz.zhjc.yxq.service.IBjzhtjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 网上、市民之家 以及网办深度统计
 */
@Service
@Slf4j
public class BjzhtjServiceImpl implements IBjzhtjService {
    @Autowired
    private RedisService redisService;
    @Autowired
    private TZhjcYxBjtjMapper bjtjMapper;
    @Autowired
    private TZhjcYxWbsdMapper wbsdMapper;

    /**
     * 网上、市民之家数据统计
     */
    @Override
    public void getOnlineAndLocalData() {
        Date today=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(today);
        //查库
        String onlineKey ="yxq-online-day"+date;
        String localKey ="yxq-underline-day"+date;
        Map result = bjtjMapper.getOnlineAndLocalData();
        //数据封装s
        Map onlineData = new HashMap();
        onlineData.put("wsysl",result.get("WSYSL"));
        onlineData.put("wsbjl",result.get("WSBJL"));
        onlineData.put("wsssl",result.get("WSSSL"));
        redisService.set(onlineKey,onlineData);
        //存redis
        Map localData = new HashMap();
        localData.put("xxsll",result.get("XXSLL"));
        localData.put("xxzbl",result.get("XXZBL"));
        localData.put("xxbjl",result.get("XXBJL"));
        redisService.set(localKey,localData);
        System.out.println("网上、市民之家数据统计");
    }

    @Override
    public void getNetDepth() {
        //查库
        String key ="yxq-wbsd-all";
        Map res = wbsdMapper.getWbsdData();
        //存redis
        Map wbsdMap = new HashMap();
        wbsdMap.put("msb",res.get("MSB"));
        wbsdMap.put("wsb",res.get("WSB"));
        wbsdMap.put("ycb",res.get("YCB"));
        wbsdMap.put("TOTAL",res.get("TOTAL"));
        redisService.set(key,wbsdMap);
        System.out.println("网办深度统计");
    }
}
