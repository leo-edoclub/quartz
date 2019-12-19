package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.JhjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 叫号机 ：排队人数，排队时间
 * Created by wang.wei on2018/11/21
 */
public class JhjJob implements BaseJob {
    @Autowired
    private JhjService jhjService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        jhjService.syncData();
    }
}
