package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class SpringContextTaskunInjectorTest {

    @Test
    public void type() throws Exception {
        assertNotNull(SpringContextTaskunInjector.class);
    }

    @Test
    public void instantiation() throws Exception {
        SpringContextTaskunInjector target = new SpringContextTaskunInjector();
        assertNotNull(target);
    }

    @Test
    public void getSchedulerConfig_A$() throws Exception {
        SpringContextTaskunInjector target = new SpringContextTaskunInjector();
        TaskunConfig actual = target.getTaskunConfig();
        assertNotNull(actual);
        assertNotNull(actual.namedServers);
        assertThat(actual.namedServers.get("frontend"), is(equalTo("(dev.example.com|www.example.com)")));
    }

    @Test
    public void inject_A$Class() throws Exception {
        SpringContextTaskunInjector target = new SpringContextTaskunInjector();
        Class<?> clazz = Taskun.class;
        Object actual = target.inject(clazz);
        assertNotNull(actual);
    }

}
