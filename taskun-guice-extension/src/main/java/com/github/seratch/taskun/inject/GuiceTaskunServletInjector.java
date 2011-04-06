package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;
import com.google.inject.Module;

public class GuiceTaskunServletInjector extends GuiceTaskunInjector implements TaskunServletInjector {

    public GuiceTaskunServletInjector(Module module) {
        super(module);
    }

    @Override
    public Taskun getTaskun() {
        return injector.getInstance(Taskun.class);
    }


}