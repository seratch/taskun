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
package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.logging.Log;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.crond.RawCrontabLine;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface Scheduler {

    void initialize(SchedulerConfig config);

    void initialize(DIContainerAdaptor containerAdaptor);

    void replaceCrontabFile(String crontabFilepath);

    void start();

    boolean isRunning();

    void shutdown();

    List<RawCrontabLine> getCurrentRawCrontabLines();

    void scheduleOnetime(Runnable runnable, Calendar scheduledDate);

    void scheduleCronExecute(Runnable runnable, RawCrontabLine crontabLine);

    void scheduleIntervalExecute(
            Runnable runnable, Calendar initialScheduledDate, long interval, TimeUnit timeUnit);


}
