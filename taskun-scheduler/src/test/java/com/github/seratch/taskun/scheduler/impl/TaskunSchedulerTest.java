package com.github.seratch.taskun.scheduler.impl;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.common.SampleComponentContainer;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.crond.RawCrontabLine;
import com.github.seratch.taskun.util.CalendarUtil;
import junit.framework.TestCase;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;

public class TaskunSchedulerTest extends TestCase {

    private Scheduler scheduler;
    private DIContainerAdaptor container;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        scheduler = new TaskunScheduler();
        container = new SampleComponentContainer();
        scheduler.initialize(container);
    }

    public void test_initialize_A$() throws Exception {
        scheduler.initialize(container);
    }

    public void test_start_A$() throws Exception {
        Scheduler target = new TaskunScheduler();
        target.initialize(container);
    }

    public void test_scheduleCronExecute_A$Runnable$String() throws Exception {
        scheduler.start();
        Runnable arg0 = new SampleWorker();
        RawCrontabLine arg1 = new RawCrontabLine("0 * * * * hoge.Foo");
        try {
            scheduler.scheduleCronExecute(arg0, arg1);
        } catch (UnsupportedOperationException e) {
        }
    }

    public void test_scheduleIntervalExecute_A$Runnable$Calendar$long$TimeUnit()
            throws Exception {
        scheduler.start();
        SampleWorker runnable = new SampleWorker();
        Calendar initialExecuteDate = CalendarUtil.getCurrentTime();
        initialExecuteDate = CalendarUtil.addSeconds(initialExecuteDate, 2);
        scheduler.scheduleIntervalExecute(runnable, initialExecuteDate, 1L,
                TimeUnit.SECONDS);
        scheduler.shutdown();
    }

    public void test_scheduleOnetime_A$Runnable$Calendar() throws Exception {
        scheduler.start();
        SampleWorker runnable = new SampleWorker();
        Calendar executeDate = CalendarUtil.getCurrentTime();
        executeDate = CalendarUtil.addSeconds(executeDate, 2);
        scheduler.scheduleOnetime(runnable, executeDate);
    }

    public void test_shutdown_A$() throws Exception {
        scheduler.start();
        scheduler.shutdown();
    }

    public void test_initialize_A$DIContainerAdaptor() throws Exception {
        scheduler.initialize(container);
    }

    public void test_replaceCrontabFile_A$String() throws Exception {
        scheduler.replaceCrontabFile("");
    }

    public void test_isRunning_A$() throws Exception {
        assertTrue(scheduler.isRunning());
    }

    public void test_getCurrentSchedulingList_A$() throws Exception {
        List<RawCrontabLine> actual = scheduler.getCurrentRawCrontabLines();
        assertNotNull(actual);
    }

    public void test_invokeCronDaemon_A$DIContainerAdaptor$ScheduledExecutorService()
            throws Exception {
        TaskunScheduler target = new TaskunScheduler();
        // given
        DIContainerAdaptor containerAdaptor = mock(DIContainerAdaptor.class);
        ScheduledExecutorService executorService = mock(ScheduledExecutorService.class);
        target.initialize(containerAdaptor);
        // when
        target.invokeCronDaemon(containerAdaptor, executorService);
        // then
        assertTrue(target.isRunning());
    }

    public void test_getDelayValue_A$Calendar$TimeUnit_day() throws Exception {
        TaskunScheduler target = new TaskunScheduler();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addDays(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.DAYS);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    public void test_getDelayValue_A$Calendar$TimeUnit_minute()
            throws Exception {
        TaskunScheduler target = new TaskunScheduler();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addMinutes(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.MINUTES);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    public void test_getDelayValue_A$Calendar$TimeUnit_second()
            throws Exception {
        TaskunScheduler target = new TaskunScheduler();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addSeconds(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.SECONDS);
        long expected = 1L;
        assertEquals(expected, actual);
    }

    public void test_getDelayValue_A$Calendar$TimeUnit_hour() throws Exception {
        TaskunScheduler target = new TaskunScheduler();
        Calendar arg0 = CalendarUtil.getCurrentTime();
        arg0 = CalendarUtil.addHours(arg0, 1);
        long actual = target.getDelayValue(arg0, TimeUnit.HOURS);
        long expected = 1L;
        assertEquals(expected, actual);
    }

}
