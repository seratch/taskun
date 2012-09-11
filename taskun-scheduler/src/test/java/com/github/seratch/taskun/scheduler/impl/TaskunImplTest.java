package com.github.seratch.taskun.scheduler.impl;

import com.github.seratch.taskun.inject.SampleTaskunInjector;
import com.github.seratch.taskun.inject.TaskunInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.github.seratch.taskun.scheduler.crond.RawCrontabLine;
import com.github.seratch.taskun.util.CalendarUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TaskunImplTest {

    private Taskun taskun;
    private TaskunInjector taskunInjector;

    @Before
    public void setUp() throws Exception {
        taskun = new TaskunImpl();
        taskunInjector = new SampleTaskunInjector();
        taskun.initialize(taskunInjector);
    }

    @Test
    public void initialize_A$() throws Exception {
        taskun.initialize(taskunInjector);
    }

    @Test
    public void start_A$() throws Exception {
        Taskun target = new TaskunImpl();
        target.initialize(taskunInjector);
    }

    @Test
    public void scheduleCronExecute_A$Runnable$String() throws Exception {
        taskun.start();
        Runnable arg0 = new SampleWorker();
        RawCrontabLine arg1 = new RawCrontabLine("0 * * * * hoge.Foo");
        try {
            taskun.scheduleCronExecute(arg0, arg1);
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void scheduleIntervalExecute_A$Runnable$Calendar$long$TimeUnit()
            throws Exception {
        taskun.start();
        SampleWorker runnable = new SampleWorker();
        Calendar initialExecuteDate = CalendarUtil.getCurrentTime();
        initialExecuteDate = CalendarUtil.addSeconds(initialExecuteDate, 2);
        taskun.scheduleIntervalExecute(runnable, initialExecuteDate, 1L,
                TimeUnit.SECONDS);
        taskun.shutdown();
    }

    @Test
    public void scheduleOnetime_A$Runnable$Calendar() throws Exception {
        taskun.start();
        SampleWorker runnable = new SampleWorker();
        Calendar executeDate = CalendarUtil.getCurrentTime();
        executeDate = CalendarUtil.addSeconds(executeDate, 2);
        taskun.scheduleOnetime(runnable, executeDate);
    }

    @Test
    public void shutdown_A$() throws Exception {
        taskun.start();
        taskun.shutdown();
    }

    @Test
    public void initialize_A$DIContainerAdaptor() throws Exception {
        taskun.initialize(taskunInjector);
    }

    @Test
    public void replaceCrontabFile_A$String() throws Exception {
        taskun.replaceCrontabFile("");
    }

    @Test
    public void isRunning_A$() throws Exception {
        assertTrue(taskun.isRunning());
    }

    @Test
    public void getCurrentSchedulingList_A$() throws Exception {
        List<RawCrontabLine> actual = taskun.getCurrentRawCrontabLines();
        assertNotNull(actual);
    }

    @Test
    public void invokeCronDaemon_A$DIContainerAdaptor$ScheduledExecutorService()
            throws Exception {
        TaskunImpl target = new TaskunImpl();
        // given
        TaskunInjector containerAdaptor = mock(TaskunInjector.class);
        ScheduledExecutorService executorService = mock(ScheduledExecutorService.class);
        target.initialize(containerAdaptor);
        // when
        target.invokeCronDaemon(containerAdaptor, executorService);
        // then
        assertTrue(target.isRunning());
    }

    @Test
    public void getDelayValue_A$Calendar$TimeUnit_day() throws Exception {
        TaskunImpl target = new TaskunImpl();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addDays(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.DAYS);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    @Test
    public void getDelayValue_A$Calendar$TimeUnit_minute()
            throws Exception {
        TaskunImpl target = new TaskunImpl();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addMinutes(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.MINUTES);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    @Test
    public void getDelayValue_A$Calendar$TimeUnit_second() throws Exception {
        TaskunImpl target = new TaskunImpl();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addSeconds(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.SECONDS);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    @Test
    public void getDelayValue_A$Calendar$TimeUnit_hour() throws Exception {
        TaskunImpl target = new TaskunImpl();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addHours(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.HOURS);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    @Test
    public void type() throws Exception {
        assertNotNull(TaskunImpl.class);
    }

    @Test
    public void instantiation() throws Exception {
        TaskunImpl target = new TaskunImpl();
        assertNotNull(target);
    }

    @Test
    public void initialize_A$TaskunConfig() throws Exception {
        TaskunImpl target = new TaskunImpl();
        TaskunConfig config = null;
        target.initialize(config);
    }

    @Test
    public void initialize_A$TaskunInjector() throws Exception {
        TaskunImpl target = new TaskunImpl();
        TaskunInjector taskunInjector = null;
        target.initialize(taskunInjector);
    }

}
