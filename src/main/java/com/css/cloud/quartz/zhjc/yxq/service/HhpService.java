package com.css.cloud.quartz.zhjc.yxq.service;

/**
 * 红黄牌service
 * Created by wang.wei on2018/9/12
 */
public interface HhpService {
    /**
     * 当天红黄牌入redis
     */
    public void insertByDay();

    /**
     * 通过远程接口调用将当天红黄牌写入redis
     */
    public void insertByRest();
}
