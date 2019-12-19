package com.css.cloud.quartz.zhdd.service.impl;

import com.css.cloud.quartz.zhdd.service.JhjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JhjServiceImpl implements JhjService {
    @Value("${jhj}")
    String url;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void syncData() {

        try {

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            String body =responseEntity.getBody();
            System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
