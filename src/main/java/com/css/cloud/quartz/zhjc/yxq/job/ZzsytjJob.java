package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.ZzsytjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 证照使用情况统计
 * Created by wang.wei on2018/9/13
 */
public class ZzsytjJob implements BaseJob {

    @Autowired
    private ZzsytjService zzsytjService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        zzsytjService.insertByMouth();
    }
}
