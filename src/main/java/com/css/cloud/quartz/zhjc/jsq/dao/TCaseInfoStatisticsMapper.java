package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.TCaseInfoStatistics;

import java.util.Date;

public interface TCaseInfoStatisticsMapper {
    int deleteByPrimaryKey(String id);

    int insert(TCaseInfoStatistics record);

    int insertSelective(TCaseInfoStatistics record);

    TCaseInfoStatistics selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TCaseInfoStatistics record);

    int updateByPrimaryKey(TCaseInfoStatistics record);

    //根据日期查是否有数据
    long selectByDate(String date);

    //根据日期更新
    int updateByDate(TCaseInfoStatistics record);

    //根据日期查数据
    TCaseInfoStatistics getDataByDate(Date date);
}