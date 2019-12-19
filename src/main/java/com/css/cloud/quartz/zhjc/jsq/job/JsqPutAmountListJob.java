package com.css.cloud.quartz.zhjc.jsq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.jsq.service.CaseStatisticService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wang.wei on2018/9/19
 */
public class JsqPutAmountListJob implements BaseJob {
    @Autowired
    CaseStatisticService caseStatisticService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        caseStatisticService.putWorkAmount();
        System.out.println ("存入网办深度Time：" );
    }
}
