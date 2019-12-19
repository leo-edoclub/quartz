package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.ZzlxtjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 证照类型统计job
 * Created by wang.wei on2018/9/13
 */
public class ZzlxtjJob implements BaseJob {
    @Autowired
    private ZzlxtjService zzlxtjService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        zzlxtjService.insertByDay();
    }
}
