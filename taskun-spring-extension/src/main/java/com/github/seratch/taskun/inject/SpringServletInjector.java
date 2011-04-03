package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;

public class SpringServletInjector extends SpringInjector implements ServletInjector {

    public SpringServletInjector() {
        super();
    }

    public SpringServletInjector(String applicationContextXML) {
        super(applicationContextXML);
    }

    @Override
    public Scheduler getScheduler() {
        return context.getBean(Scheduler.class);
    }

}
