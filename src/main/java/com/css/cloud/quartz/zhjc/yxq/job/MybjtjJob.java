package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.IMybjtjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 每月办件统计job
 * Created by wang.wei on2018/9/13
 */
public class MybjtjJob implements BaseJob {
    @Autowired
    private IMybjtjService mybjtjService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        mybjtjService.getBjsjData();
        mybjtjService.getBjzhqkData();
    }
}
