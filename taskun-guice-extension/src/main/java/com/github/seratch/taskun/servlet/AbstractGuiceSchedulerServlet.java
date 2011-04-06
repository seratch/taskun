package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.GuiceTaskunServletInjector;
import com.google.inject.Module;

public abstract class AbstractGuiceSchedulerServlet extends AbstractTaskunServlet {


    public abstract Module[] getPreparedModules();

    @Override
    protected void prepareToInit() {
        setTaskunInjector(new GuiceTaskunServletInjector(getPreparedModules()));
    }


}
