package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SpringInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(SpringInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        SpringInjector target = new SpringInjector();
        assertNotNull(target);
    }

    @Test
    public void getSchedulerConfig_A$() throws Exception {
        SpringInjector target = new SpringInjector();
        SchedulerConfig actual = target.getSchedulerConfig();
        SchedulerConfig expected = null;
        assertNotNull(actual);
    }

    @Test
    public void inject_A$Class() throws Exception {
        SpringInjector target = new SpringInjector();
        Class<?> clazz = Scheduler.class;
        Object actual = target.inject(clazz);
        assertNotNull(actual);
    }

}
