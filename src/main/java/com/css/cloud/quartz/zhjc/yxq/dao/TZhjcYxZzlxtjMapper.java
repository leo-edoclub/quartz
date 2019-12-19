package com.css.cloud.quartz.zhjc.yxq.dao;

import com.css.cloud.quartz.zhjc.yxq.entity.TZhjcYxZzlxtj;

import java.util.List;
import java.util.Map;

/**
 * 查询电子证照类型
 */
public interface TZhjcYxZzlxtjMapper {
    /**
     * 查询当前时间、当天的电子证照
     * @param today
     * @return
     */
   public List<Map> selectZzlxByDay(String today);

    /**
     * 当天证照类型 总数、新增总数
     * @param today
     * @return
     */
   public Map selectZzlxTotal(String today);
}