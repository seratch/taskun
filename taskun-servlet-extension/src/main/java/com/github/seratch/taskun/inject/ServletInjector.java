package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;

public interface ServletInjector extends Injector {

    Scheduler getScheduler();

}
