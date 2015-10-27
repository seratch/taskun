/*
 * Copyright 2011 Kazuhiro Sera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.github.seratch.taskun.scheduler.impl;

import com.github.seratch.taskun.inject.TaskunInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.github.seratch.taskun.scheduler.crond.CronInvocation;
import com.github.seratch.taskun.scheduler.crond.RawCrontabLine;
import com.github.seratch.taskun.util.CalendarUtil;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TaskunImpl implements Taskun {

    private ScheduledExecutorService executorService;

    private TaskunInjector taskunInjector;

    private CronInvocation cronInvocation = new CronInvocation();

    private String crontabFilepath;

    public void initialize(TaskunConfig config) {
        final TaskunConfig config_ = config;
        initialize(new TaskunInjector() {
            @Override
            public TaskunConfig getTaskunConfig() {
                return config_;
            }

            @SuppressWarnings("unchecked")
            @Override
            public <T> T inject(Class<?> clazz) {
                try {
                    return (T) clazz.newInstance();
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    public void initialize(TaskunInjector taskunInjector) {
        this.taskunInjector = taskunInjector;
        executorService = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            private ThreadGroup threadGroup = new ThreadGroup("taskun-scheduler");

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(threadGroup, r);
                t.setDaemon(true);
                t.setName(t.getThreadGroup().getName() + "-worker-" + t.getId());
                return t;
            }
        });
    }

    @Override
    public void start() {
        invokeCronDaemon(taskunInjector, executorService);
    }

    @Override
    public void scheduleCronExecute(Runnable runnable, RawCrontabLine crontabLine) {
        cronInvocation.addCrontabLine(crontabLine);
    }

    @Override
    public void scheduleIntervalExecute(Runnable runnable, Calendar initialScheduledDate, long interval, TimeUnit timeUnit) {
        long initialDelay = getDelayValue(initialScheduledDate, timeUnit);
        executorService.scheduleAtFixedRate(runnable, initialDelay, interval, timeUnit);
    }

    @Override
    public void scheduleOnetime(Runnable runnable, Calendar scheduledDate) {
        Calendar currentDate = CalendarUtil.getCurrentTime();
        long delay = scheduledDate.getTimeInMillis() - currentDate.getTimeInMillis();
        executorService.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public void shutdown() {
        executorService.shutdownNow();
    }

    @Override
    public void replaceCrontabFile(String crontabFilepath) {
        this.crontabFilepath = crontabFilepath;
    }

    @Override
    public boolean isRunning() {
        return !(executorService == null || executorService.isTerminated());
    }

    @Override
    public List<RawCrontabLine> getCurrentRawCrontabLines() {
        return cronInvocation.getCurrentRawCrontabLines();
    }

    @Override
    public long getLastCronInvocationTimeMillis() {
        return cronInvocation.getPreviousCheckedTimeMillis();
    }

    void invokeCronDaemon(TaskunInjector taskunInjector, ScheduledExecutorService executorService) {
        if (taskunInjector == null) {
            throw new IllegalStateException("Not initialized scheduler - the taskunInjector is null");
        }
        if (crontabFilepath != null) {
            cronInvocation.initialize(taskunInjector, executorService, crontabFilepath);
        } else {
            cronInvocation.initialize(taskunInjector, executorService);
        }
        Calendar cal = CalendarUtil.getCurrentTime();
        long currentTime = cal.getTimeInMillis();
        cal.add(Calendar.MINUTE, 1);
        cal.set(Calendar.SECOND, 0);
        long initialInvocation = cal.getTimeInMillis();
        long initialDelay = initialInvocation - currentTime;
        executorService.scheduleAtFixedRate(cronInvocation, initialDelay, 60000L, TimeUnit.MILLISECONDS);
    }

    long getDelayValue(Calendar executeDate, TimeUnit timeUnit) {
        Calendar current = CalendarUtil.getCurrentTime();
        long delayMillis = executeDate.getTimeInMillis() - current.getTimeInMillis();
        if (timeUnit.equals(TimeUnit.DAYS)) {
            return delayMillis / 1000 / 60 / 60 / 24;
        } else if (timeUnit.equals(TimeUnit.HOURS)) {
            return delayMillis / 1000 / 60 / 60;
        } else if (timeUnit.equals(TimeUnit.MICROSECONDS)) {
            return delayMillis * 1000;
        } else if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
            return delayMillis;
        } else if (timeUnit.equals(TimeUnit.MINUTES)) {
            return delayMillis / 1000 / 60;
        } else if (timeUnit.equals(TimeUnit.NANOSECONDS)) {
            return delayMillis * 1000 * 1000;
        } else if (timeUnit.equals(TimeUnit.SECONDS)) {
            return delayMillis / 1000;
        } else {
            throw new UnsupportedOperationException("unexpected TimeUnit value");
        }
    }

}
