package com.css.cloud.quartz.zhjc.jsq.service;

public interface CaseStatisticService {
     void  saveRedisData(String key, Object value);

    /**
     * 基础库新增数据统计
     */
    void putDbBaseData();

    /*
      *延期办结率
     */
    void putHandleDelayData();

    /*
     *每日办理情况
     */
    void putEverydayCondition();
    void getEverydayCondition();
    /*
     *办件量统计
     */
    void putWorkAmount();
    void getWorkAmount();
    /*
     *热点事项
     */
    void putHotItem();
    /*
     事务认领
     */
    void putMatterReceive();
}
