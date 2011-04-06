package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.S2TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.servlet.impl.S2SchedulerServlet;

public class SnippetS2SchedulerServlet extends S2SchedulerServlet {

    @Override
    protected void prepareToInit() {
        S2TaskunServletInjector injectorTaskun = new S2TaskunServletInjector();
        Taskun taskun = injectorTaskun.getTaskun();
        taskun.replaceCrontabFile("snippet_crontab.txt");
        super.setTaskunInjector(injectorTaskun);
    }

}
