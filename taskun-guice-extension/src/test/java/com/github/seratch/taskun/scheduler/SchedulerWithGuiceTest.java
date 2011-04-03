package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.inject.GuiceInjector;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import com.google.inject.AbstractModule;
import org.junit.Test;

public class SchedulerWithGuiceTest {

    @Test
    public void using() throws Exception {
        Scheduler scheduler = new TaskunScheduler();
        GuiceInjector injector = new GuiceInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        scheduler.initialize(injector);
        scheduler.start();
        Thread.sleep(2000L);
    }
}
