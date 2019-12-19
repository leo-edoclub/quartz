package com.css.cloud.quartz.zhjc.yxq.dao;

import java.util.Map;

import java.util.Map;

public interface TZhjcYxBjtjMapper {
    /**
     * 查询当前时间、当天办件总量
     * @param day
     * @return
     */
    Map selectBjtjByDay(String day);



    Map<String,Object> getOnlineAndLocalData();


    Map getSljByMonth(String month);
}