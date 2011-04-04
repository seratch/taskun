package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.google.inject.Module;

public class GuiceServletInjector extends GuiceInjector implements ServletInjector {

    public GuiceServletInjector(Module... modules) {
        super(modules);
    }

    @Override
    public Scheduler getScheduler() {
        return injector.getInstance(Scheduler.class);
    }


}