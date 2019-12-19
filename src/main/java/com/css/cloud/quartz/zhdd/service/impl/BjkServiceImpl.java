package com.css.cloud.quartz.zhdd.service.impl;

import com.css.cloud.quartz.zhdd.service.BjkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BjkServiceImpl implements BjkService {

    @Value("${bjk1}")
    String url;
    @Value("${bjk2}")
    String url2;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void getBjl() {
        try {

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            String body =responseEntity.getBody();
            System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getHhp() {
        try {

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url2,String.class);
            String body =responseEntity.getBody();
            System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
