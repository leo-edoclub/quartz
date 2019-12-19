package com.css.cloud.quartz.zhdd.service.impl;

import com.css.cloud.quartz.zhdd.service.TykfptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TykfptServiceImpl implements TykfptService {

    @Value("${tykfpt1}")
    String url;
    @Value("${tykfpt2}")
    String url2;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void getCsb() {

        try {

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            String body =responseEntity.getBody();
            System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getTjsj() {

        try {
            System.out.println(url2);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url2,String.class);
            String body =responseEntity.getBody();
            System.out.println(" tygzpt body ================"+body+"============="+responseEntity.getStatusCode());
        }catch (Exception e){
            System.out.println("tygzpt getTjsj fail ========"+e.getMessage());
            e.printStackTrace();
        }
    }
}
