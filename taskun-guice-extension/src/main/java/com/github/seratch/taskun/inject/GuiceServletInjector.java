package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;
import com.google.inject.Module;

public class GuiceServletInjector extends GuiceInjector implements TaskunServletInjector {

    public GuiceServletInjector(Module module) {
        super(module);
    }

    @Override
    public Taskun getTaskun() {
        return injector.getInstance(Taskun.class);
    }


}