package com.css.cloud.quartz.zhjc.jsq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.jsq.service.PingFenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 评分考核指标定时任务reids   以小项展示（展示小项的横向柱状图）
 * Created by wang.wei on2018/9/19
 */
public class JsqKhzbJob implements BaseJob {
    @Autowired
    private PingFenService pingFenService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        pingFenService.syncData4KHZB();
    }
}
