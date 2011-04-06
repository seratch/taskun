package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.google.inject.AbstractModule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GuiceTaskunInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(GuiceTaskunInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        GuiceTaskunInjector target = new GuiceTaskunInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        assertNotNull(target);
    }

    @Test
    public void inject_A$Class() throws Exception {
        GuiceTaskunInjector target = new GuiceTaskunInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        Class<?> clazz = GuiceTaskunInjectorTest.class;
        Object actual = target.inject(clazz);
        assertNotNull(actual);
    }

    @Test
    public void getTaskunConfig_A$() throws Exception {
        GuiceTaskunInjector target = new GuiceTaskunInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });
        TaskunConfig actual = target.getTaskunConfig();
        assertNotNull(actual);
    }

}
