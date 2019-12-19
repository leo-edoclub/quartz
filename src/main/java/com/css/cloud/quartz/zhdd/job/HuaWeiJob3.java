package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.HuaWeiService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class HuaWeiJob3 implements BaseJob {
    @Autowired
    private HuaWeiService huaWeiService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        huaWeiService.getTjsj();
    }
}
