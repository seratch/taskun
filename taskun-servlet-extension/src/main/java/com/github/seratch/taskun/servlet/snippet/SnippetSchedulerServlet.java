package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import com.github.seratch.taskun.servlet.impl.DefaultSchedulerServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SnippetSchedulerServlet extends DefaultSchedulerServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void prepareToInit() {
		setContainerAdaptor(new DIContainerAdaptor() {

			@Override
			public SchedulerConfig getSchedulerConfig() {
				SchedulerConfig config = new SchedulerConfig();
				config.enableInvokingScheduler = true;
				return config;
			}

			@Override
			public Scheduler getScheduler() {
				Scheduler scheduler = new TaskunScheduler();
				scheduler.replaceCrontabFile("snippet_crontab.txt");
				return scheduler;
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
		});
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setStatus(200);
		resp.getWriter().write("SnippetSchedulerServlet has invoked!");
	}

}
