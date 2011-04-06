package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.inject.S2TaskunInjector;
import org.junit.Test;

public class SchedulerWithS2Test {

    @Test
    public void using() throws Exception {
        Taskun taskun = TaskunFactory.getInstance();
        S2TaskunInjector taskunInjector = new S2TaskunInjector();
        taskun.initialize(taskunInjector);
        taskun.start();
        Thread.sleep(2000L);
    }

}
