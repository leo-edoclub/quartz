package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.RdsxService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 热点事项job
 * Created by wang.wei on2018/9/11
 */
public class RdsxJob implements BaseJob {
    @Autowired
    private RdsxService rdsxService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        rdsxService.init();
        rdsxService.insertByMonth();
    }
}
