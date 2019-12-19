package com.css.cloud.quartz.zhdd.service.impl;

import com.css.cloud.quartz.zhdd.service.HuaWeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wang.wei on2018/11/21
 */
@Service
public class HuaWeiServiceImpl implements HuaWeiService {
    @Value("${huawei1}")
    String url ;
    @Value("${huawei2}")
    String url2 ;
    @Value("${huawei3}")
    String url3 ;
    @Value("${huawei4}")
    String url4 ;
    @Value("${huawei5}")
    String url5;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void syncData() {
//        String url = "http://192.168.31.136:8086/zhdd/huawei/getWarn";
        try {
            System.out.println(url);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            System.out.println("同步数据syncData");
            System.out.println(responseEntity.getStatusCode());
            String body =responseEntity.getBody();
            System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getXX() {
        try {
            System.out.println("获取信息getXX");
            System.out.println(url2);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url2,String.class);
            System.out.println(responseEntity.getStatusCode());
            String body =responseEntity.getBody();
            System.out.println(body);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getTjsj() {
        try {

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url3,String.class);
            String body =responseEntity.getBody();
            System.out.println(url3+"=============="+body+"=============="+responseEntity.getStatusCode());
        }catch (Exception e){
            System.out.println("huawei3 fail============="+e.getMessage());
        }
    }

    @Override
    public void getSb() {
        try {
            System.out.println(url4);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url4,String.class);
            String body =responseEntity.getBody();
            System.out.println("huawei4 getsb body "+body+"========="+responseEntity.getStatusCode());
        }catch (Exception e){
            System.out.println(" huawei4 getsb fail======="+e.getMessage());
        }
    }

    @Override
    public void getHostCcqk() {
        try {
            System.out.println(url5);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url5, String.class);
            String body = responseEntity.getBody();
            System.out.println("huawei5 getHostCcqk body " + body + "=========" + responseEntity.getStatusCode());
        } catch (Exception e) {
            System.out.println(" huawei5 getHostCcqk fail=======" + e.getMessage());
        }
    }
}
