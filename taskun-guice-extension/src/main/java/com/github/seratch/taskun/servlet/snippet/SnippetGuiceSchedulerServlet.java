package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.TaskunFactory;
import com.github.seratch.taskun.servlet.AbstractGuiceSchedulerServlet;
import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class SnippetGuiceSchedulerServlet extends AbstractGuiceSchedulerServlet {

    @Override
    public Module[] getPreparedModules() {
        return new Module[]{new AbstractModule() {
            @Override
            protected void configure() {
                Taskun taskun = TaskunFactory.getInstance();
                taskun.replaceCrontabFile("snippet_crontab.txt");
                bind(Taskun.class).toInstance(taskun);
            }
        }};
    }

}
