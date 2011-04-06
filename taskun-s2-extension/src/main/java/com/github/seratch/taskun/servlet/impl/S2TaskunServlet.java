package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.inject.S2TaskunServletInjector;
import com.github.seratch.taskun.servlet.AbstractTaskunServlet;

public class S2TaskunServlet extends AbstractTaskunServlet {

    @Override
    protected void prepareToInit() {
        super.setTaskunInjector(new S2TaskunServletInjector());
    }

}
