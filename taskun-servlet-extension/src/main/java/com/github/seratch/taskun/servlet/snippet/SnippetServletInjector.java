package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.ServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;

public class SnippetServletInjector implements ServletInjector {

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
    public <T> T inject(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
