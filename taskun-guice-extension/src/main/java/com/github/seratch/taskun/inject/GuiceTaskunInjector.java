package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.google.inject.Guice;
import com.google.inject.Module;

public class GuiceTaskunInjector implements TaskunInjector {

    protected com.google.inject.Injector injector;

    public GuiceTaskunInjector(Module... modules) {
        this.injector = Guice.createInjector(modules);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T inject(Class<?> clazz) {
        return (T) injector.getInstance(clazz);
    }

    @Override
    public TaskunConfig getTaskunConfig() {
        return injector.getInstance(TaskunConfig.class);
    }

}
