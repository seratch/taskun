package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.google.inject.AbstractModule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GuiceInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(GuiceInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        GuiceInjector target = new GuiceInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        assertNotNull(target);
    }

    @Test
    public void inject_A$Class() throws Exception {
        GuiceInjector target = new GuiceInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        Class<?> clazz = GuiceInjectorTest.class;
        Object actual = target.inject(clazz);
        assertNotNull(actual);
    }

    @Test
    public void getSchedulerConfig_A$() throws Exception {
        GuiceInjector target = new GuiceInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        SchedulerConfig actual = target.getSchedulerConfig();
        assertNotNull(actual);
    }

}
