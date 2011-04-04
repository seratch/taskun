package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.inject.ServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import com.github.seratch.taskun.servlet.AbstractSchedulerServlet;
import com.github.seratch.taskun.util.StringUtil;

import java.io.IOException;
import java.util.Properties;

public class DefaultSchedulerServlet extends AbstractSchedulerServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void prepareToInit() {

		ServletInjector injector = new ServletInjector() {

			@Override
			public SchedulerConfig getSchedulerConfig() {

				Properties props = new Properties();
				try {
					ClassLoader classLoader = DefaultSchedulerServlet.class.getClassLoader();
					props.load(classLoader.getResourceAsStream("taskun.properties"));
				} catch (IOException e) {
					throw new IllegalStateException("taskun.properties not found!");
				}

				SchedulerConfig config = new SchedulerConfig();
				config.enableInvokingScheduler
						= Boolean.valueOf((String) props.get("enableInvokingScheduler"));
				config.enableLoggingForEachCrondInvocation
						= Boolean.valueOf((String) props.get("enableLoggingForEachCrondInvocation"));

				for (int i = 0; i < 10; i++) {
					String namedServerN = (String) props.get("namedServer" + i);
					if (!StringUtil.isEmpty(namedServerN))
						config.namedServers.put("namedServer" + i, namedServerN);
				}

				return config;
			}

			@Override
			public Scheduler getScheduler() {
				return new TaskunScheduler();
			}

			@Override
			@SuppressWarnings("unchecked")
			public <T> T inject(Class<?> clazz) {
				try {
					return (T) clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};

		super.setInjector(injector);

	}

}
