package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class S2TaskunInjector implements TaskunInjector {

    protected S2Container s2Container;

    public S2TaskunInjector() {
        SingletonS2ContainerFactory.init();
        s2Container = SingletonS2ContainerFactory.getContainer();
    }

    @Override
    public TaskunConfig getTaskunConfig() {
        return (TaskunConfig) s2Container.getComponent(TaskunConfig.class);
    }

    @Override
    public <T> T inject(Class<?> clazz) {
        return (T) s2Container.getComponent(clazz);
    }

}
