package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;

public class SampleInjector implements Injector {

    public SchedulerConfig config = new SchedulerConfig();

    @Override
    public SchedulerConfig getSchedulerConfig() {
        SchedulerConfig config = new SchedulerConfig();
        return config;
    }

    @Override
    public <T> T inject(Class<?> clazz) {
        return null;
    }

}
