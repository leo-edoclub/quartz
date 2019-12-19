package com.css.cloud.quartz.zhjc.jsq.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TDeftScoreStatisticsMapper {

    List<Map<String,String>> readRecorcByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
    int insertByPO(@Param("list") List<Map> list);
}