package com.css.cloud.quartz.zhjc.jsq.dao;

import com.css.cloud.quartz.zhjc.jsq.entity.TDeftAreaOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TDeftAreaOrderMapper {
    List<Map<String,String>> readRecorcByJgAndRegin(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 定期汇总
     * @param list
     * @return
     */
    int insertByPO(@Param("list") List<TDeftAreaOrder> list);

    /**
     * 删除指定时间段数据
     * 一般用于插入之前删除
     * @param startTime
     * @param endTime
     * @return
     */
    int deleteByWeek(@Param("startTime") String startTime, @Param("endTime") String endTime);
}