package com.css.cloud.quartz.zhjc.yxq.job;

import com.css.cloud.quartz.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
public class HelloJob implements BaseJob {
    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);
    public HelloJob() {
          
    }  
     @Async
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
        _log.error("Hello Job执行时间: " + new Date());  
          
    }  
}  
