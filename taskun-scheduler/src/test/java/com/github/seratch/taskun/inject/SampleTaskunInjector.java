package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;

public class SampleTaskunInjector implements TaskunInjector {

    public TaskunConfig config = new TaskunConfig();

    @Override
    public TaskunConfig getTaskunConfig() {
        TaskunConfig config = new TaskunConfig();
        return config;
    }

    @Override
    public <T> T inject(Class<?> clazz) {
        return null;
    }

}
