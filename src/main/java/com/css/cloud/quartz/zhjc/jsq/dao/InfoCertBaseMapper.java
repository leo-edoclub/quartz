package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.InfoCertBase;

public interface InfoCertBaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(InfoCertBase record);

    int insertSelective(InfoCertBase record);

    InfoCertBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InfoCertBase record);

    int updateByPrimaryKey(InfoCertBase record);
}