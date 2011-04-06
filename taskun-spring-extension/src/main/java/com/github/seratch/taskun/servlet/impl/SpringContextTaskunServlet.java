package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.inject.SpringContextTaskunServletInjector;
import com.github.seratch.taskun.servlet.AbstractTaskunServlet;

public class SpringContextTaskunServlet extends AbstractTaskunServlet {

    @Override
    protected void prepareToInit() {
        super.setTaskunInjector(new SpringContextTaskunServletInjector());
    }

}
