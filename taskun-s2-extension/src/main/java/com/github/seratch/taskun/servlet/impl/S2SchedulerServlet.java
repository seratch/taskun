package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.inject.S2ServletInjector;
import com.github.seratch.taskun.servlet.AbstractSchedulerServlet;

public class S2SchedulerServlet extends AbstractSchedulerServlet {

    @Override
    protected void prepareToInit() {
        super.setInjector(new S2ServletInjector());
    }

}
