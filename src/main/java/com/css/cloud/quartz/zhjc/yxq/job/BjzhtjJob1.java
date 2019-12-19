package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.yxq.service.IBjzhtjService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 网上、市民之家办事综合信息job
 * Created by wang.wei on2018/9/13
 */
public class BjzhtjJob1 implements BaseJob {
    @Autowired
    private IBjzhtjService bjzhtjService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        bjzhtjService.getOnlineAndLocalData();
    }
}
