package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Scheduler;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SpringServletInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(SpringServletInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        SpringServletInjector target = new SpringServletInjector();
        assertNotNull(target);
    }

    @Test
    public void getScheduler_A$() throws Exception {
        SpringServletInjector target = new SpringServletInjector();
        Scheduler actual = target.getScheduler();
    }

}
