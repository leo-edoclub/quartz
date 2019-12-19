package com.css.cloud.quartz.zhjc.yxq.service;

/**
 * 热点事项service
 * Created by wang.wei on2018/9/11
 */
public interface RdsxService {
    /**
     * 热点事项入redis 之初始化
     */
    public void init();

    /**
     * 根据月份向redis中注入数据
     */
    public void insertByMonth();
}
