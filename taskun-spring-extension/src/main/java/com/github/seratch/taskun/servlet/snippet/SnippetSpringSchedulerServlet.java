package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.SpringContextTaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.servlet.impl.SpringSchedulerServlet;

public class SnippetSpringSchedulerServlet extends SpringSchedulerServlet {

    @Override
    protected void prepareToInit() {
        super.setTaskunInjector(new SpringContextTaskunServletInjector("snippet_applicationContext.xml") {
            @Override
            public Taskun getTaskun() {
                Taskun taskun = context.getBean(Taskun.class);
                taskun.replaceCrontabFile("snippet_crontab.txt");
                return taskun;
            }
        });
    }

}
