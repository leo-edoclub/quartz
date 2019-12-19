package com.css.cloud.quartz.zhjc.jsq.dao;


import com.css.cloud.quartz.zhjc.jsq.entity.THandleOfEveryday;

public interface THandleOfEverydayMapper {
    int deleteByPrimaryKey(String id);

    int insert(THandleOfEveryday record);

    int insertSelective(THandleOfEveryday record);

    THandleOfEveryday selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(THandleOfEveryday record);

    int updateByPrimaryKey(THandleOfEveryday record);
    // 库里是否有当日数据
    long countByDate(String date);
    // 更新当日数据
    int updateByDate(THandleOfEveryday record);
    // 获取当日数据
    THandleOfEveryday getByDate(String today);
}