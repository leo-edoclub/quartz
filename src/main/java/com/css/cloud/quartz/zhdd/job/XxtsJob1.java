package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.XxtsService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class XxtsJob1 implements BaseJob {
    @Autowired
    XxtsService xxtsService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        xxtsService.xxtsByMonth();
    }
}
