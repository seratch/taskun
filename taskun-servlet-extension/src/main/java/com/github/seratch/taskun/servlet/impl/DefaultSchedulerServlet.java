package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.common.DIContainerAdaptor;
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

		DIContainerAdaptor container = new DIContainerAdaptor() {

			@Override
			public SchedulerConfig getSchedulerConfig() {
				Properties props = new Properties();
				try {
					props.load(DefaultSchedulerServlet.class.getClassLoader()
							.getResourceAsStream("taskun.properties"));
				} catch (IOException e) {
					throw new IllegalStateException(
							"taskun.properties not found!");
				}
				SchedulerConfig config = new SchedulerConfig();
				config.enableInvokingScheduler = Boolean.valueOf((String) props
						.get("enableInvokingScheduler"));
				config.enableLoggingForEachCrondInvocation = Boolean
						.valueOf((String) props
								.get("enableLoggingForEachCrondInvocation"));
				String namedServer1 = (String) props.get("namedServer1");
				if (!StringUtil.isEmpty(namedServer1))
					config.namedServers.put("namedServer1", namedServer1);
				String namedServer2 = (String) props.get("namedServer2");
				if (!StringUtil.isEmpty(namedServer2))
					config.namedServers.put("namedServer2", namedServer2);
				String namedServer3 = (String) props.get("namedServer3");
				if (!StringUtil.isEmpty(namedServer3))
					config.namedServers.put("namedServer3", namedServer3);
				return config;
			}

			@Override
			public Scheduler getScheduler() {
				return new TaskunScheduler();
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T> T getComponent(Class<?> clazz) {
				try {
					return (T) clazz.newInstance();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};

		Scheduler scheduler = container.getScheduler();
		scheduler.replaceCrontabFile("crontab.txt");
		setContainerAdaptor(container);
	}

}
