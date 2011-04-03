package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.logging.Log4jLogImpl;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;
import org.junit.Test;

public class SchedulerWithLog4JTest {

    @Test
    public void using() throws Exception {
        Scheduler scheduler = new TaskunScheduler();
        SchedulerConfig config = new SchedulerConfig();
        config.setLogImplClass(Log4jLogImpl.class);
        scheduler.initialize(config);
        scheduler.start();
        Thread.sleep(2000L);
    }

}
