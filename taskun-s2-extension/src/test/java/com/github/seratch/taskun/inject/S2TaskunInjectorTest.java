package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class S2TaskunInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(S2TaskunInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        S2TaskunInjector target = new S2TaskunInjector();
        assertNotNull(target);
    }

    @Test
    public void getSchedulerConfig_A$() throws Exception {
        S2TaskunInjector target = new S2TaskunInjector();
        TaskunConfig actual = target.getTaskunConfig();
        assertNotNull(actual);
    }

    @Test
    public void inject_A$Class() throws Exception {
        S2TaskunInjector target = new S2TaskunInjector();
        Class<?> clazz = TaskunConfig.class;
        Object actual = target.inject(clazz);
        assertNotNull(actual);
    }

}
