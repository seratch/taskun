package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class S2Injector implements Injector {

    protected S2Container s2Container;

    public S2Injector() {
        SingletonS2ContainerFactory.init();
        s2Container = SingletonS2ContainerFactory.getContainer();
    }

    public SchedulerConfig getSchedulerConfig() {
        return (SchedulerConfig) s2Container.getComponent(SchedulerConfig.class);
    }

    public <T> T inject(Class<?> clazz) {
        return (T) s2Container.getComponent(clazz);
    }
}
