package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.inject.SampleTaskunInjector;
import com.github.seratch.taskun.inject.TaskunInjector;
import com.github.seratch.taskun.logging.TaskunLog;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class CronInvocationTest {


    @Test
    public void type() throws Exception {
        assertNotNull(CronInvocation.class);
    }

    @Test
    public void instantiation() throws Exception {
        CronInvocation target = new CronInvocation();
        assertNotNull(target);
    }

    CronInvocation crond = new CronInvocation();

    @Before
    public void setup() {
        ScheduledExecutorService service = Mockito.mock(ScheduledExecutorService.class);
        crond.initialize(new SampleTaskunInjector(), service);
    }

    @Test
    public void getLog_A$String() throws Exception {
        String name = "aaa";
        TaskunLog actual = crond.getLog(name);
        assertNotNull(actual);
    }

    @Test
    public void run_A$() throws Exception {
        crond.run();
    }

    @Test
    public void getCommandWorker_A$Crontab() throws Exception {
        Crontab crontabLine = new Crontab();
        crontabLine.commandClassName = new CrontabCommandClassNameElement("com.github.seratch.taskun.scheduler.impl.SampleWorker");
        Runnable actual = crond.getCommandWorker(crontabLine);
        assertNotNull(actual);
    }

    @Test
    public void initialize_A$TaskunInjector$ScheduledExecutorService() throws Exception {
        TaskunInjector taskunInjector = new SampleTaskunInjector();
        ScheduledExecutorService executorService = null;
        crond.initialize(taskunInjector, executorService);
    }

    @Test
    public void initialize_A$TaskunInjector$ScheduledExecutorService$String() throws Exception {
        TaskunInjector taskunInjector = new SampleTaskunInjector();
        ScheduledExecutorService executorService = Mockito.mock(ScheduledExecutorService.class);
        String crontabFilepath = "sdfsafa";
        crond.initialize(taskunInjector, executorService, crontabFilepath);
    }

    @Test
    public void addCrontabLine_A$RawCrontabLine() throws Exception {
        RawCrontabLine line = new RawCrontabLine("*/1 * * * * tmp");
        crond.addCrontabLine(line);
    }

    @Test
    public void loggingAtEachInvocation_A$String() throws Exception {
        String message = "sdfsdfsa";
        crond.loggingAtEachInvocation(message);
    }

    @Test
    public void getCurrentRawCrontabLines_A$() throws Exception {
        List<RawCrontabLine> actual = crond.getCurrentRawCrontabLines();
        assertNotNull(actual);
    }

    @Test
    public void getPreviousCheckedTimeMillis_A$() throws Exception {
        long actual = crond.getPreviousCheckedTimeMillis();
        long expected = 0L;
        assertEquals(expected, actual);
    }

    @Test
    public void getLog_A$() throws Exception {
        CronInvocation target = new CronInvocation();
        TaskunLog actual = target.getLog();
        assertNotNull(actual);
    }

}
