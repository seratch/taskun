package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.impl.TaskunImpl;
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
                bind(Taskun.class).to(TaskunImpl.class);
            }
        });
        Taskun actual = target.getTaskun();
        assertNotNull(actual);
    }

}
