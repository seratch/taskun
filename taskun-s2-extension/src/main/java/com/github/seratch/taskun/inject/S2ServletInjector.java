package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;

public class S2ServletInjector extends S2Injector implements ServletInjector {

    public Scheduler getScheduler() {
        return (Scheduler) s2Container.getComponent(Scheduler.class);
    }

}
