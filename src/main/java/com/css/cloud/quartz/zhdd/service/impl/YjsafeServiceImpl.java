package com.css.cloud.quartz.zhdd.service.impl;

import com.css.cloud.quartz.zhdd.service.YjsafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YjsafeServiceImpl implements YjsafeService {

    @Value("${safelog}")
    String url;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void getSafelog() {
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            String body =responseEntity.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
