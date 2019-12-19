package com.css.cloud.quartz.zhjc.yxq.service.impl;

import com.css.cloud.quartz.common.RedisService;
import com.css.cloud.quartz.zhjc.yxq.dao.TZhjcYxSxrzMapper;
import com.css.cloud.quartz.zhjc.yxq.service.ISxrzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事项入驻与联通统计
 */
@Service
@Slf4j
public class SxrzServiceImpl implements ISxrzService{
    @Autowired
    private TZhjcYxSxrzMapper sxrzMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @SuppressWarnings("rawtypes")
    public void getSxrzData() {
        //入驻与联通是相关联的
        Date today=new Date();
        String date = this.transferDate(today,"yyyyMMdd");
        //echarts格式
        Map<String,Object> rzResult = new HashMap<String,Object>();
        NumberFormat nf =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);        //保留到小数点后几位
        //echarts格式
        Map<String,Object> ltResult = new HashMap<String,Object>();
        //查库
        List<Map<String,BigDecimal>> sxrzList = sxrzMapper.getSxrzData();
        if(sxrzList!=null && sxrzList.size()!=0){
            Map<String,BigDecimal> sxMap = sxrzMapper.getSxrzData().get(0);
            rzResult.put("sxzs",sxMap.get("ZS"));//事项总数
            rzResult.put("rzzs",sxMap.get("RZSX"));//入驻总数
            rzResult.put("tyspzs",sxMap.get("TYZS"));//通用审批入驻数
            rzResult.put("szxtzs",sxMap.get("ZJZS"));//市值系统入驻数
            rzResult.put("sjxtzs",sxMap.get("SJZS"));//上级系统入驻数

            ltResult.put("rzzs",sxMap.get("RZSX"));//入驻总数
            ltResult.put("sxlt",sxMap.get("LTSX"));//互联互通总数
            ltResult.put("tyspzs",sxMap.get("TYZS"));//通用审批入驻数
            ltResult.put("szxtzs",sxMap.get("ZJZS"));//市直系统入驻数
            ltResult.put("sjxtzs",sxMap.get("SJZS"));//上级系统入驻数
            ltResult.put("tysplt",sxMap.get("TYLT"));//通用系统联通数
            ltResult.put("szxtlt",sxMap.get("ZJLT"));//市直系统联通数
            ltResult.put("sjxtlt",sxMap.get("SJLT"));//上级系统联通数
            ltResult.put("tyspwlt",(sxMap.get("TYZS").intValue()-sxMap.get("TYLT").intValue()));//通用系统未联通数
            ltResult.put("szxtwlt",(sxMap.get("ZJZS")).intValue()-(sxMap.get("ZJLT").intValue()));//市直系统未联通数
            ltResult.put("sjxtwlt",(sxMap.get("SJZS")).intValue()-(sxMap.get("SJLT")).intValue());//上级系统未联通数
            if(sxMap.get("TYZS")!=null && sxMap.get("TYZS").equals("0")){
                ltResult.put("tyspltl",0);
            }else{
                ltResult.put("tyspltl",nf.format((sxMap.get("TYLT")).intValue()/(sxMap.get("TYZS")).floatValue()));
            }
            if(sxMap.get("ZJZS")!=null && sxMap.get("ZJZS").equals("0")){
                ltResult.put("szxtltl",0);
            }else{
                ltResult.put("szxtltl",nf.format((sxMap.get("ZJLT")).intValue()/(sxMap.get("ZJZS")).floatValue()));
            }
            if(sxMap.get("SJZS")!=null && sxMap.get("SJZS").equals("0")){
                ltResult.put("sjxtltl",0);
            }else{
                ltResult.put("sjxtltl",nf.format((sxMap.get("SJLT")).intValue()/(sxMap.get("SJZS")).floatValue()));
            }
            ltResult.put("tysplts",sxMap.get("TYLT")+"/"+sxMap.get("TYZS"));
            ltResult.put("szxtlts",sxMap.get("ZJLT")+"/"+sxMap.get("ZJZS"));
            ltResult.put("sjxtlts",sxMap.get("SJLT")+"/"+sxMap.get("SJZS"));
        }else{
            rzResult.put("sxzs",0);
            rzResult.put("rzzs",0);
            rzResult.put("tyspzs",0);
            rzResult.put("szxtzs",0);
            rzResult.put("sjxtzs",0);

            ltResult.put("rzzs",0);
            ltResult.put("sxlt",0);
            ltResult.put("tyspzs",0);
            ltResult.put("szxtzs",0);
            ltResult.put("sjxtzs",0);
            ltResult.put("tysplt",0);
            ltResult.put("szxtlt",0);
            ltResult.put("sjxtlt",0);
            ltResult.put("tyspwlt",0);
            ltResult.put("szxtwlt",0);
            ltResult.put("sjxtwlt",0);
            ltResult.put("tyspltl",0);
            ltResult.put("szxtltl",0);
            ltResult.put("sjxtltl",0);
            ltResult.put("tysplts",0);
            ltResult.put("szxtlts",0);
            ltResult.put("sjxtlts",0);
        }
        //存到redis
        String key1 = "yxq-sxrz-day-"+date;
        redisService.set(key1,rzResult);





        //存到redis
        String key2 = "yxq-sxht-day-"+date;
        redisService.set(key2,ltResult);
        System.out.println("入驻事项统计");
    }

    //日期转换字符串
    public String transferDate(Date day,String style){
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.format(day);
    }
}
