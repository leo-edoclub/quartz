package com.css.cloud.quartz.zhjc.yxq.dao;

import com.css.cloud.quartz.zhjc.yxq.entity.TZhjcYxRdsx;

import java.util.List;
import java.util.Map;

/**
 * 热点事项dao
 */
public interface TZhjcYxRdsxMapper {
    /**
     * 初始化以往数据，只执行一次即可
     * @return
     */
    List<Map> init();

    /**
     * 按照月份统计全市热点事项
     * @param mouth
     * @return
     */
    List<Map> selectRdsxByMouth(String mouth);
    /**
     * 按照行政区划、月份统计热点事项
     * @param reginCode
     * @return
     */
    TZhjcYxRdsx selectRdsxByreginCode(String reginCode);
}