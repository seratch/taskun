package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.inject.S2Injector;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import org.junit.Test;

public class SchedulerWithS2Test {

    @Test
    public void using() throws Exception {
        Scheduler scheduler = new TaskunScheduler();
        S2Injector injector = new S2Injector();
        scheduler.initialize(injector);
        scheduler.start();
        Thread.sleep(2000L);
    }

}
