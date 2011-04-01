package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;

public class SampleContainer implements DIContainerAdaptor {

	public SchedulerConfig config = new SchedulerConfig();

	@Override
	public Scheduler getScheduler() {
		return new TaskunScheduler();
	}

	@Override
	public SchedulerConfig getSchedulerConfig() {
		SchedulerConfig config = new SchedulerConfig();
		return config;
	}

	@Override
	public <T> T getComponent(Class<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
