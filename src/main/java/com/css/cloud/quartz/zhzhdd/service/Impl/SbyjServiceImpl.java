package com.css.cloud.quartz.zhzhdd.service.Impl;

import com.css.cloud.quartz.zhzhdd.service.SbyjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 实时预警
 * @author gao
 */
@Service
@Transactional
@Slf4j
public class SbyjServiceImpl implements SbyjService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void saveYjxx() {
        String url = "http://192.168.31.136:8086/zhdd/huawei/run";
        try{
            restTemplate.getForEntity(url,Map.class);
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}
