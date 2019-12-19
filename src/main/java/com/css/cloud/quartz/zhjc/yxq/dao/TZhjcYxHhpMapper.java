package com.css.cloud.quartz.zhjc.yxq.dao;

import java.util.Map;

public interface TZhjcYxHhpMapper {
    /**
     * 查询当天红黄牌
     * @param day
     * @return
     */
    public Map selectHhpByDay(String day);

}