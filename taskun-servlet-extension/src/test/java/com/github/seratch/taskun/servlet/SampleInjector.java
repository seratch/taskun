package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.TaskunFactory;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;

public class SampleInjector implements TaskunServletInjector {

    @Override
    public Taskun getTaskun() {
        return TaskunFactory.getInstance();
    }

    @Override
    public TaskunConfig getTaskunConfig() {
        TaskunConfig config = new TaskunConfig();
        return config;
    }

    @Override
    public <T> T inject(Class<?> arg0) {
        return null;
    }

}
