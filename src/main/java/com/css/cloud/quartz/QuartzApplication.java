package com.css.cloud.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan({"com.css.cloud.quartz.dao","com.css.cloud.quartz.zhjc.yxq.dao","com.css.cloud.quartz.zhjc.jsq.dao"})
@SpringBootApplication
public class QuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}
}
