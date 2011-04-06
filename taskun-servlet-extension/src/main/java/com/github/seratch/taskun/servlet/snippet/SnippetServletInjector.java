package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.TaskunFactory;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;

public class SnippetServletInjector implements TaskunServletInjector {

    public TaskunConfig config = new TaskunConfig();

    @Override
    public Taskun getTaskun() {
        Taskun taskun = TaskunFactory.getInstance();
        taskun.replaceCrontabFile("snippet_crontab.txt");
        return taskun;
    }

    @Override
    public TaskunConfig getTaskunConfig() {
        TaskunConfig config = new TaskunConfig();
        return config;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T inject(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
