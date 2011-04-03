package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import com.google.inject.AbstractModule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GuiceServletInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(GuiceServletInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        GuiceServletInjector target = new GuiceServletInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        assertNotNull(target);
    }

    @Test
    public void getScheduler_A$() throws Exception {
        GuiceServletInjector target = new GuiceServletInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(Scheduler.class).to(TaskunScheduler.class);
            }
        });
        Scheduler actual = target.getScheduler();
        assertNotNull(actual);
    }

}
