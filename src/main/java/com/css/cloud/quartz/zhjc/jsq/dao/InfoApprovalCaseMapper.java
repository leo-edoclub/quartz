package com.css.cloud.quartz.zhjc.jsq.dao;

import com.css.cloud.quartz.zhjc.jsq.entity.InfoApprovalCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InfoApprovalCaseMapper {
    int deleteByPrimaryKey(String caseinfoId);

    int insert(InfoApprovalCase record);

    int insertSelective(InfoApprovalCase record);

    InfoApprovalCase selectByPrimaryKey(String caseinfoId);

    int updateByPrimaryKeySelective(InfoApprovalCase record);

    int updateByPrimaryKey(InfoApprovalCase record);

    //获取表总数
    Map<String,Object> getNumByDate(String date);

    //获取每日受理办结
    Map<String,Object> getDailyIncrease(String date);
    //获取平均受理办结
    Map<String,Object> getAvgWeekly(@Param("today") String today, @Param("oldDay") String oldDay);
    //获取不同来源的统计
    Map<String,Object> getAmountBySource(int flag);

    //获取热点事项列表
    List<Map> getMostItem();

    //获取网办深度
    Map<String,Object> getDepthCase();

}