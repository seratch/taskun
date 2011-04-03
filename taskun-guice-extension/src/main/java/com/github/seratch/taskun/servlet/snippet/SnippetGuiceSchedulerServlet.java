package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import com.github.seratch.taskun.servlet.AbstractGuiceSchedulerServlet;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class SnippetGuiceSchedulerServlet extends AbstractGuiceSchedulerServlet {

    @Override
    public Module getPreparedModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                Scheduler scheduler = new TaskunScheduler();
                scheduler.replaceCrontabFile("snippet_crontab.txt");
                bind(Scheduler.class).toInstance(scheduler);
            }
        };
    }

}
