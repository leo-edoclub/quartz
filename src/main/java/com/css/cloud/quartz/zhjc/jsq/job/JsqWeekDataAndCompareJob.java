package com.css.cloud.quartz.zhjc.jsq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.jsq.service.PingFenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缓存 每个机构近六周的数据 以及和其他机构的对比
 * Created by wang.wei on2018/9/19
 */
public class JsqWeekDataAndCompareJob implements BaseJob {
    @Autowired
    private PingFenService pingFenService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        pingFenService.syncWeekData();
    }
}
