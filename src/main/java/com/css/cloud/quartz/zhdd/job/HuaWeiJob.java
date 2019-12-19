package com.css.cloud.quartz.zhdd.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhdd.service.HuaWeiService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 华为硬件
 * Created by wang.wei on2018/11/21
 */
public class HuaWeiJob implements BaseJob {
    @Autowired
    private  HuaWeiService huaWeiService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        huaWeiService.syncData();
    }
}
