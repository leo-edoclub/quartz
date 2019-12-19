package com.css.cloud.quartz.zhjc.yxq.dao;

import java.util.List;
import java.util.Map;

/**
 * 证照使用情况
 */
public interface TZhjcYxZzsytjMapper {
    /**
     * 按月查询证照使用情况如
     * @param mouth
     * @return
     */
    public List<Map> selectZzsyByMouth(String mouth);
}