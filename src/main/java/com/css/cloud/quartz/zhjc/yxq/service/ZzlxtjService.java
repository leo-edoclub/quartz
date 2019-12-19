package com.css.cloud.quartz.zhjc.yxq.service;

/**
 * 证照类型统计service
 * Created by wang.wei on2018/9/12
 */
public interface ZzlxtjService {
    /**
     * 根据月份向redis中注入数据
     */
    public void insertByDay();
}
