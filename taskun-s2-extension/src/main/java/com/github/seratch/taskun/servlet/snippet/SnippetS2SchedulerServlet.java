package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.S2ServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.servlet.impl.S2SchedulerServlet;

public class SnippetS2SchedulerServlet extends S2SchedulerServlet {

    @Override
    protected void prepareToInit() {
        S2ServletInjector injector = new S2ServletInjector();
        Scheduler scheduler = injector.getScheduler();
        scheduler.replaceCrontabFile("snippet_crontab.txt");
        super.setInjector(injector);
    }

}
