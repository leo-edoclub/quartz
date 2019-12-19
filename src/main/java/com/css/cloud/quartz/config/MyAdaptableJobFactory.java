package com.css.cloud.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wang.wei on2018/8/13
 * 重写createJobInstance 目的：创建job instance之后，返回之前，将此job instance 注入到spring  容器
 */
@Component
public class MyAdaptableJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
      Object obj = super.createJobInstance(bundle);
      autowireCapableBeanFactory.autowireBean(obj);
      return obj;
    }
}
