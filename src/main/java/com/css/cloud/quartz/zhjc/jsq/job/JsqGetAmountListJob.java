package com.css.cloud.quartz.zhjc.jsq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.jsq.service.CaseStatisticService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wang.wei on2018/9/19
 */
public class JsqGetAmountListJob implements BaseJob {
    @Autowired
    CaseStatisticService caseStatisticService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        caseStatisticService.getWorkAmount();
        System.out.println ("取出网办深度Time：" );
    }
}
