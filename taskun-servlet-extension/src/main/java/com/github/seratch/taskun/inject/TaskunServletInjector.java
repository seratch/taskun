package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;

public interface TaskunServletInjector extends Injector {

    Scheduler getScheduler();

}
