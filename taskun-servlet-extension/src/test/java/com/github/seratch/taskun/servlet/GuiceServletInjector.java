package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.ServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceServletInjector implements ServletInjector {

    Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            Scheduler scheduler = new TaskunScheduler();
            scheduler.replaceCrontabFile("snippet_crontab.txt");
            bind(Scheduler.class).toInstance(scheduler);
        }
    });

    @Override
    @SuppressWarnings("unchecked")
    public <T> T inject(Class<?> clazz) {
        return (T) injector.getInstance(clazz);
    }

    @Override
    public Scheduler getScheduler() {
        return injector.getInstance(Scheduler.class);
    }

    @Override
    public SchedulerConfig getSchedulerConfig() {
        return injector.getInstance(SchedulerConfig.class);
    }

}
