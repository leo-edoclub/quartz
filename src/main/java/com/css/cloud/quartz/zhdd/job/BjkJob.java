package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.BjkService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class BjkJob implements BaseJob {

    @Autowired
    private BjkService bjkService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        bjkService.getBjl();
    }
}
