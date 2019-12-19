package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.SxService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class SxJob implements BaseJob {
    @Autowired
    private SxService sxService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        sxService.getSxbg();
    }
}
