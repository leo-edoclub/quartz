package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.InfoPerson;

public interface InfoPersonMapper {
    int deleteByPrimaryKey(String personId);

    int insert(InfoPerson record);

    int insertSelective(InfoPerson record);

    InfoPerson selectByPrimaryKey(String personId);

    int updateByPrimaryKeySelective(InfoPerson record);

    int updateByPrimaryKey(InfoPerson record);
}