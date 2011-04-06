package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;

public class SpringContextTaskunServletInjector extends SpringContextTaskunInjector implements TaskunServletInjector {

    public SpringContextTaskunServletInjector() {
        super();
    }

    public SpringContextTaskunServletInjector(String applicationContextXML) {
        super(applicationContextXML);
    }

    @Override
    public Taskun getTaskun() {
        return context.getBean(Taskun.class);
    }

}
