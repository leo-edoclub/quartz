package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.InfoEnterPrise;

public interface InfoEnterPriseMapper {
    int deleteByPrimaryKey(String entId);

    int insert(InfoEnterPrise record);

    int insertSelective(InfoEnterPrise record);

    InfoEnterPrise selectByPrimaryKey(String entId);

    int updateByPrimaryKeySelective(InfoEnterPrise record);

    int updateByPrimaryKey(InfoEnterPrise record);
}