package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.inject.TaskunInjector;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunImpl;

public final class TaskunFactory {

    private TaskunFactory() {
    }

    public static Taskun getInstance() {
        return new TaskunImpl();
    }

    public static Taskun getInstance(TaskunConfig config) {
        Taskun taskun = new TaskunImpl();
        taskun.initialize(config);
        return taskun;
    }

    public static Taskun getInstance(TaskunInjector injector) {
        Taskun taskun = new TaskunImpl();
        taskun.initialize(injector);
        return taskun;
    }

}
