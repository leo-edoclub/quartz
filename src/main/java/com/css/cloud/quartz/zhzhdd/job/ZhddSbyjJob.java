package com.css.cloud.quartz.zhzhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhzhdd.service.SbyjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实时预警
 * @author gao
 */
public class ZhddSbyjJob implements BaseJob {
    @Autowired
    private SbyjService sbyjService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        sbyjService.saveYjxx();
    }
}
