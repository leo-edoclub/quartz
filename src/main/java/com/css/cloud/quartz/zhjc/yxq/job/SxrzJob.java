package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.ISxrzService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class SxrzJob implements BaseJob {
    @Autowired
    private ISxrzService sxrzService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        sxrzService.getSxrzData();
    }
}
