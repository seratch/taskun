package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.inject.GuiceTaskunInjector;
import com.google.inject.AbstractModule;
import org.junit.Test;

public class SchedulerWithGuiceTest {

    @Test
    public void using() throws Exception {
        Taskun scheduler = TaskunFactory.getInstance();
        GuiceTaskunInjector taskunInjector = new GuiceTaskunInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        scheduler.initialize(taskunInjector);
        scheduler.start();
        Thread.sleep(2000L);
    }
}
