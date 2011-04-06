package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.google.inject.Guice;
import com.google.inject.Module;

public class GuiceInjector implements Injector {

	protected com.google.inject.Injector injector;

	public GuiceInjector(Module... modules) {
		this.injector = Guice.createInjector(modules);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T inject(Class<?> clazz) {
		return (T) injector.getInstance(clazz);
	}

	@Override
	public SchedulerConfig getSchedulerConfig() {
		return injector.getInstance(SchedulerConfig.class);
	}

}
