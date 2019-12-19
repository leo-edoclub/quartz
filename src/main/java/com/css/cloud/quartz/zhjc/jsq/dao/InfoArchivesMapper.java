package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.InfoArchives;

public interface InfoArchivesMapper {
    int deleteByPrimaryKey(String id);

    int insert(InfoArchives record);

    int insertSelective(InfoArchives record);

    InfoArchives selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InfoArchives record);

    int updateByPrimaryKey(InfoArchives record);
}