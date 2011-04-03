package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.SpringServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.servlet.impl.SpringSchedulerServlet;

public class SnippetSpringSchedulerServlet extends SpringSchedulerServlet {

    @Override
    protected void prepareToInit() {
        super.setInjector(new SpringServletInjector("snippet_applicationContext.xml") {
            @Override
            public Scheduler getScheduler() {
                Scheduler scheduler = context.getBean(Scheduler.class);
                scheduler.replaceCrontabFile("snippet_crontab.txt");
                return scheduler;
            }
        });
    }

}
