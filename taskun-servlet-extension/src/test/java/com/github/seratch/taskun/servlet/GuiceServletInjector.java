package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.TaskunFactory;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceServletInjector implements TaskunServletInjector {

    Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            Taskun taskun = TaskunFactory.getInstance();
            taskun.replaceCrontabFile("snippet_crontab.txt");
            bind(Taskun.class).toInstance(taskun);
        }
    });

    @Override
    @SuppressWarnings("unchecked")
    public <T> T inject(Class<?> clazz) {
        return (T) injector.getInstance(clazz);
    }

    @Override
    public Taskun getTaskun() {
        return injector.getInstance(Taskun.class);
    }

    @Override
    public TaskunConfig getTaskunConfig() {
        return injector.getInstance(TaskunConfig.class);
    }

}
