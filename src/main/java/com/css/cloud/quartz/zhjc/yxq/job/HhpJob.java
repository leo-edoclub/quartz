package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.HhpService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wang.wei on2018/9/13
 */
public class HhpJob implements BaseJob {
    @Autowired
    private HhpService hhpService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        hhpService.insertByDay();
        hhpService.insertByRest();
    }
}
