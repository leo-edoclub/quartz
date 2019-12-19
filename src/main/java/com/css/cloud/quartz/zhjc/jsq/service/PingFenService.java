package com.css.cloud.quartz.zhjc.jsq.service;

/**
 * Created by wang.wei on2018/8/7
 */
public interface PingFenService {
    //雷达图
    void syncData();
    //柱状图
    void syncData4ZZT();
    //考核指标
    void syncData4KHZB();
    //所有机构，所有类型数据查询
    void syncAllData();
    //从机构、区划的维度统计分数
    void saveScoreToDBByOrg();
    //从分类的维度统计分数
   void saveScoreToDBByType();
   //每个机构近六周的数据 以及和其他机构的对比
    void syncWeekData();
}
