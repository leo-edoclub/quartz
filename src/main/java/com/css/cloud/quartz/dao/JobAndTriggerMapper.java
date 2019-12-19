package com.css.cloud.quartz.dao;


import com.css.cloud.quartz.entity.JobAndTrigger;

import java.util.List;

public interface JobAndTriggerMapper {
	public List<JobAndTrigger> getJobAndTriggerDetails();
}
