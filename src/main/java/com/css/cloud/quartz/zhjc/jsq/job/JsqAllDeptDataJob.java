package com.css.cloud.quartz.zhjc.jsq.job;

import com.css.cloud.quartz.job.BaseJob;
import com.css.cloud.quartz.zhjc.jsq.service.PingFenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  一张网数据（可以准实时更新）（八个圆环）
 * 柱状图  每隔柱状图分8类或者5类
 * Created by wang.wei on2018/9/19
 */
public class JsqAllDeptDataJob implements BaseJob {
    @Autowired
    private PingFenService pingFenService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        pingFenService.syncAllData();
    }
}
