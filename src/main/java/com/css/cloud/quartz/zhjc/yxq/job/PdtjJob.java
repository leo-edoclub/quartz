package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.IPdtjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 排队统计job
 * Created by wang.wei on2018/9/14
 */
public class PdtjJob implements BaseJob {
    @Autowired
    private IPdtjService pdtjService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        pdtjService.getPdsjData();
        System.out.println("排队统计");
    }
}
