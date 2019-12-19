package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.TDeftOverDelay;

public interface TDeftOverDelayMapper {
    int deleteByPrimaryKey(String id);

    int insert(TDeftOverDelay record);

    int insertSelective(TDeftOverDelay record);

    TDeftOverDelay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TDeftOverDelay record);

    int updateByPrimaryKey(TDeftOverDelay record);
}