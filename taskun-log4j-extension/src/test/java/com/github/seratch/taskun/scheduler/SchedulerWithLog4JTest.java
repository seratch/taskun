package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.logging.TaskunLogLog4jImpl;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.junit.Test;

public class SchedulerWithLog4JTest {

    @Test
    public void using() throws Exception {
        Taskun taskun = TaskunFactory.getInstance();
        TaskunConfig config = new TaskunConfig();
        config.setLogImplClass(TaskunLogLog4jImpl.class);
        taskun.initialize(config);
        taskun.start();
        Thread.sleep(2000L);
    }

}
