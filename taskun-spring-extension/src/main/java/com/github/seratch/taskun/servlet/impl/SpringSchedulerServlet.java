package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.inject.SpringContextTaskunServletInjector;
import com.github.seratch.taskun.servlet.AbstractSchedulerServlet;

public class SpringSchedulerServlet extends AbstractSchedulerServlet {

    @Override
    protected void prepareToInit() {
        super.setTaskunInjector(new SpringContextTaskunServletInjector());
    }

}
