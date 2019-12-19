package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.BjkService;
import com.css.cloud.quartz.zhdd.service.YjsafeService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class YjsafeJob implements BaseJob {

    @Autowired
    private YjsafeService yjsafeService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        yjsafeService.getSafelog();
    }
}
