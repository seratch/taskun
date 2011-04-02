package com.github.seratch.taskun.common;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;

public class SampleComponentContainer implements DIContainerAdaptor {

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
    public <T> T getComponent(Class<?> clazz) {
        return null;
    }

}
