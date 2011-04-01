package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;

public class SnippetContainerAdaptor implements DIContainerAdaptor {

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
	@SuppressWarnings("unchecked")
	public <T> T getComponent(Class<?> clazz) {
		try {
			return (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
