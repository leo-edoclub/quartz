package com.css.cloud.quartz.zhjc.yxq.dao;

import com.css.cloud.quartz.zhjc.yxq.entity.TZhjcYxMypdtj;

import java.util.Map;

public interface TZhjcYxMypdtjMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ZHJC_YX_MYPDTJ
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ZHJC_YX_MYPDTJ
     *
     * @mbggenerated
     */
    int insert(TZhjcYxMypdtj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ZHJC_YX_MYPDTJ
     *
     * @mbggenerated
     */
    int insertSelective(TZhjcYxMypdtj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ZHJC_YX_MYPDTJ
     *
     * @mbggenerated
     */
    TZhjcYxMypdtj selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ZHJC_YX_MYPDTJ
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TZhjcYxMypdtj record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ZHJC_YX_MYPDTJ
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TZhjcYxMypdtj record);

    //排队时间统计
    Map getPdsjData(String month);
}