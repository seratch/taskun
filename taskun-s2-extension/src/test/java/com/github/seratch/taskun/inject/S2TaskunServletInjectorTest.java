package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class S2TaskunServletInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(S2TaskunServletInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        S2TaskunServletInjector target = new S2TaskunServletInjector();
        assertNotNull(target);
    }

    @Test
    public void getScheduler_A$() throws Exception {
        S2TaskunServletInjector target = new S2TaskunServletInjector();
        Taskun actual = target.getTaskun();
        assertNotNull(actual);
    }

}
