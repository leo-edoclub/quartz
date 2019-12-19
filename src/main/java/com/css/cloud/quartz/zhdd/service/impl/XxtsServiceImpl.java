package com.css.cloud.quartz.zhdd.service.impl;

import com.css.cloud.quartz.zhdd.service.XxtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class XxtsServiceImpl implements XxtsService {

//    @Value("${xxts}")
    String url;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void xxtsByWeek() {
        this.send("3");

    }

    @Override
    public void xxtsByMonth() {
        this.send("2");
    }

    @Override
    public void xxtsByQuarter() {
        this.send("1");
    }
    public void send(String sjzqbm){
        String realUrl = url+"?sjzqbm="+sjzqbm;
        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(realUrl,String.class);
            String body =responseEntity.getBody();
            System.out.println(body);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
