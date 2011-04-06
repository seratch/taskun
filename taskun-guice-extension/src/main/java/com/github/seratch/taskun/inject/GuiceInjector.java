package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.google.inject.*;

public class GuiceInjector implements TaskunInjector {

    protected com.google.inject.Injector injector;

    public GuiceInjector(Module module) {
        this.injector = Guice.createInjector(module);
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
