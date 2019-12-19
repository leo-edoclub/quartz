package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.TykfptService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class TykfptJob implements BaseJob {

    @Autowired
    private TykfptService tykfptService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        tykfptService.getCsb();
    }
}
