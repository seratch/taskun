package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.TaskunFactory;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.github.seratch.taskun.servlet.AbstractTaskunServlet;
import com.github.seratch.taskun.util.StringUtil;

import java.io.IOException;
import java.util.Properties;

public class SimpleTaskunServlet extends AbstractTaskunServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void prepareToInit() {
        TaskunServletInjector taskunInjector = new TaskunServletInjector() {

            @Override
            public TaskunConfig getTaskunConfig() {

                Properties props = new Properties();
                try {
                    props.load(SimpleTaskunServlet.class.getClassLoader().getResourceAsStream("taskun.properties"));
                } catch (IOException e) {
                    throw new IllegalStateException("taskun.properties not found!");
                }

                TaskunConfig config = new TaskunConfig();
                config.enableInvokingScheduler = Boolean.valueOf((String) props.get("enableInvokingScheduler"));
                config.enableLoggingForEachCrondInvocation = Boolean.valueOf((String) props.get("enableLoggingForEachCrondInvocation"));

                for (int i = 0; i < 10; i++) {
                    String namedServerN = (String) props.get("namedServer" + i);
                    if (!StringUtil.isEmpty(namedServerN)) {
                        config.namedServers.put("namedServer" + i, namedServerN);
                    }
                }
                return config;
            }

            @Override
            public Taskun getTaskun() {
                return TaskunFactory.getInstance();
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
        super.setTaskunInjector(taskunInjector);
    }

}
