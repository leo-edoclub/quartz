package com.css.cloud.quartz.zhjc.jsq.dao;

import com.css.cloud.quartz.zhjc.jsq.entity.TDeftAreaOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InfoScoreStatisticsMapper {
    /**
     * 查考核评分的标准，按小项展示
     * @return
     */
    List<Map<String,String>> readKhbzDataByDate(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 查字典 小分类
     * @return
     */
    List<Map<String,String>> readDictByby1();

    /**
     * 查询字典PGXM 的分类
     * @return
     */
    List<Map<String,String>> readDictNamedPGXM();

    /**
     * 统计所有部门、行政区划的数据，按照八大类分类，形成柱状图
     * @return
     */
    List<Map<String,String>> readAllDataByDate(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查询部门和区划信息 给一张网使用
     * @return
     */
    List<Map<String,String>> readDeptAndxqzhByPid();

    /**
     * 以大分类的维度  查询出一周的分数汇总
     * 排名
     * @return
     */
    List<Map> readScoreByType(@Param("startTime") String startTime, @Param("endTime") String endTime);
    /**
     * 统计部门、行政区划一周的总分
     * 排名
     * @return
     */
    List<TDeftAreaOrder> readScoreByOrg(@Param("startTime") String startTime, @Param("endTime") String endTime);
    /**
     * 传寻 近 6周的数据，展示成折线图
     * @return
     */
    List<Map>  readWeekData();
}