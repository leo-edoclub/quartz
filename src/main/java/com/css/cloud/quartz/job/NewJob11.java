package com.css.cloud.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class NewJob11 implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(NewJob11.class);

    public NewJob11() {
          
    }  
     
    public void execute(JobExecutionContext context)  
        throws JobExecutionException {  
        _log.error("New Job1111111111执行时间: " + new Date());
          
    }  
}  