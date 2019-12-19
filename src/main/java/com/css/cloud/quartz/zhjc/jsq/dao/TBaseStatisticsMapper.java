package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.TBaseStatistics;

import java.util.List;

public interface TBaseStatisticsMapper {
    int deleteByPrimaryKey(String id);

    int insert(TBaseStatistics record);

    int insertSelective(TBaseStatistics record);

    TBaseStatistics selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TBaseStatistics record);

    int updateByPrimaryKey(TBaseStatistics record);

    //批量插入
    void insertAll(List<TBaseStatistics> list);

}