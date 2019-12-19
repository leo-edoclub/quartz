package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.IBmdfService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 部门得分job
 * Created by wang.wei on2018/9/13
 */
public class BmdfJob implements BaseJob {
    @Autowired
    private IBmdfService bmdfService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        bmdfService.getBmdfData();
    }
}
