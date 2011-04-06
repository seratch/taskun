package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.inject.TaskunInjector;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TaskunFactoryTest {

    @Test
    public void type() throws Exception {
        assertNotNull(TaskunFactory.class);
    }

    @Test
    public void getInstance_A$() throws Exception {
        Taskun actual = TaskunFactory.getInstance();
        assertNotNull(actual);
    }

    @Test
    public void getInstance_A$TaskunConfig() throws Exception {
        TaskunConfig config = null;
        Taskun actual = TaskunFactory.getInstance(config);
        assertNotNull(actual);
    }

    @Test
    public void getInstance_A$TaskunInjector() throws Exception {
        TaskunInjector injector = null;
        Taskun actual = TaskunFactory.getInstance(injector);
        assertNotNull(actual);
    }

}
