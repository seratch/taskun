package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SpringServletInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(SpringContextTaskunServletInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        SpringContextTaskunServletInjector target = new SpringContextTaskunServletInjector();
        assertNotNull(target);
    }

    @Test
    public void getScheduler_A$() throws Exception {
        SpringContextTaskunServletInjector target = new SpringContextTaskunServletInjector();
        Taskun actual = target.getTaskun();
    }

}
