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
package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.inject.Injector;
import com.github.seratch.taskun.logging.Log;
import com.github.seratch.taskun.logging.UtilLoggerImpl;
import com.github.seratch.taskun.scheduler.CurrentServer;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.util.CalendarUtil;
import com.github.seratch.taskun.util.IOCloser;
import com.github.seratch.taskun.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CronDaemon implements Runnable {

    static final String DEFAULT_CRONTAB_FILENAME = "crontab.txt";

    static final String LOG_PREFIX = "[Crond] ";


    private boolean isInitialized = false;

    private ScheduledExecutorService executorService;

    private Injector injector;

    private CrontabRepository crontabRepos = new CrontabRepository(
            new ArrayList<Crontab>());

    private long previousCheckedTimeMillis = 0L;

    private String thisServerNameIfGiven;

    private String thisServerHostname;


    private CrontabParser parser;

    private Class<? extends Log> logImplClass = UtilLoggerImpl.class;

    private Log log = getLog(CronDaemon.class.getCanonicalName());

    Log getLog() {
        return getLog(CronDaemon.class.getCanonicalName());
    }

    Log getLog(String name) {
        try {
            return logImplClass.getConstructor(StringUtil.class).newInstance();
        } catch (Throwable t) {
            return new UtilLoggerImpl(name);
        }
    }


    @Override
    public void run() {
        loggingAtEachInvocation(LOG_PREFIX + "Previous : "
                + new Date(previousCheckedTimeMillis).toString() + " ("
                + previousCheckedTimeMillis + ")");
        if (!isInitialized) {
            throw new IllegalStateException("Need to initialize CronDaemon!");
        }
        Calendar current = CalendarUtil.getCurrentTime();
        loggingAtEachInvocation(LOG_PREFIX + "Current  : "
                + current.getTime().toString() + " ("
                + current.getTimeInMillis() + ")");
        long currentCheckTime = current.getTimeInMillis();
        for (Crontab crontab : crontabRepos.getCrontabLines()) {
            if (crontab.isIntervalInvocation) {
                continue;
            }
            // check the host
            if (crontab.namedServers != null) {
                boolean isHostToWork = false;
                for (String name : crontab.namedServers) {
                    if (thisServerNameIfGiven.equals(name)) {
                        isHostToWork = true;
                        break;
                    }
                }
                if (!isHostToWork) {
                    continue;
                }
            }
            loggingAtEachInvocation(LOG_PREFIX + "Scheduled : "
                    + crontab.rawLine + " (Next:"
                    + new Date(crontab.nextInvocationTime).toString() + ")");
            // check invocation timestamp
            if (crontab.nextInvocationTime > previousCheckedTimeMillis
                    && crontab.nextInvocationTime <= currentCheckTime) {
                try {
                    Runnable instance = getCommandWorker(crontab);
                    long multiplicity = crontab.multiplicity;
                    long initialDelay = crontab.initialIntervalSeconds * 1000;
                    for (int i = 0; i < multiplicity; i++) {
                        initialDelay += 100L; // distribute threads
                        executorService.schedule(instance, initialDelay,
                                TimeUnit.MILLISECONDS);
                        loggingAtEachInvocation(LOG_PREFIX + ">> Invoked : "
                                + crontab.commandClassName);
                    }
                    crontab.nextInvocationTime = parser.getNextInvocationTime(
                            current, crontab);
                    loggingAtEachInvocation(LOG_PREFIX + ">> Renewed : "
                            + crontab.rawLine + "(Next:"
                            + new Date(crontab.nextInvocationTime).toString()
                            + ")");
                } catch (Exception e) {
                    log.error(this.getClass().getCanonicalName()
                            + " failed to execute scheduled task : "
                            + crontab.commandClassName);
                    e.printStackTrace();
                }
            }
        }
        previousCheckedTimeMillis = currentCheckTime;
    }

    Runnable getCommandWorker(Crontab crontabLine) throws Exception {
        Class<?> clazz = Class.forName(crontabLine.commandClassName.toString());
        Runnable instance = null;
        try {
            instance = injector.inject(clazz);
        } catch (Exception e) {
            log.debug("Command class load failed! class name : "
                    + clazz.getCanonicalName());
        }
        if (instance == null) {
            instance = (Runnable) clazz.newInstance();
        }
        return instance;
    }

    public void initialize(Injector container,
                           ScheduledExecutorService executorService) {
        initialize(container, executorService, DEFAULT_CRONTAB_FILENAME);
    }

    public void initialize(Injector containerAdaptor,
                           ScheduledExecutorService executorService, String crontabFilepath) {

        this.injector = containerAdaptor;

        SchedulerConfig config = containerAdaptor.getSchedulerConfig();
        if (config != null) {
            this.logImplClass = config.getLogImplClass();
            this.log = getLog();
        }

        this.parser = new CrontabParser(this.logImplClass);

        this.executorService = executorService;
        InputStream is = null;
        BufferedReader br = null;
        try {
            // read crontab.txt
            is = this.getClass().getClassLoader()
                    .getResourceAsStream(crontabFilepath);
            if (is == null) {
                log.info("Skipped the crontab scheduing" + " because "
                        + crontabFilepath + " did not found.");
                return;
            }
            br = new BufferedReader(new InputStreamReader(is));
            String lineString = null;
            while ((lineString = br.readLine()) != null) {
                crontabRepos.add(parser.parseLine(new RawCrontabLine(lineString)));
            }
            // working server config
            thisServerHostname = CurrentServer.getHostname();
            thisServerNameIfGiven = CurrentServer
                    .getServerName(containerAdaptor.getSchedulerConfig());
            if (StringUtil.isEmpty(thisServerNameIfGiven)) {
                thisServerNameIfGiven = thisServerHostname;
            }
            // start interval invocations
            List<Crontab> newList = new ArrayList<Crontab>();
            log.info("----- Taskun-scheduler initialized -----");
            log.info("Working at " + thisServerNameIfGiven + "("
                    + thisServerHostname + ")");
            for (Crontab crontab : crontabRepos.getCrontabLines()) {
                if (crontab.isIntervalInvocation) {
                    try {
                        // check the host
                        if (crontab.namedServers != null) {
                            boolean isHostToWork = false;
                            for (String name : crontab.namedServers) {
                                if (thisServerNameIfGiven.equals(name)) {
                                    isHostToWork = true;
                                    break;
                                }
                            }
                            if (!isHostToWork) {
                                continue;
                            }
                        }
                        // invoke command
                        Runnable command = getCommandWorker(crontab);
                        long multiplicity = crontab.multiplicity;
                        long initialDelay = crontab.initialIntervalSeconds * 1000;
                        long delay = crontab.intervalSeconds * 1000;
                        for (int i = 0; i < multiplicity; i++) {
                            initialDelay += 100L; // distribute threads
                            executorService.scheduleAtFixedRate(command,
                                    initialDelay, delay, TimeUnit.MILLISECONDS);
                        }
                        log.info("Interval invocation : "
                                + crontab.intervalSeconds + "sec,"
                                + crontab.commandClassName + ","
                                + crontab.multiplicity);
                    } catch (Exception e) {
                        log.error(this.getClass().getCanonicalName()
                                + " failed to execute scheduled task : "
                                + crontab.commandClassName);
                        e.printStackTrace();
                    }
                } else {
                    newList.add(crontab);
                }
            }
            crontabRepos = new CrontabRepository(newList);
            for (Crontab crontab : crontabRepos.getCrontabLines()) {
                try {
                    Class.forName(crontab.commandClassName.toString());
                } catch (ClassNotFoundException e) {
                    throw new IllegalStateException(
                            "Crontab file load error! (cannot load command class : "
                                    + crontab.commandClassName + ")");
                }
                log.info("Crontab invocation : " + crontab.rawLine);
            }
        } catch (IOException e) {
            log.error("Cannot read crontab.txt because of "
                    + e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            IOCloser.close(is);
            IOCloser.close(br);
            isInitialized = true;
            log.info("----------------------------------------");
        }
    }

    public void addCrontabLine(RawCrontabLine line) {
        crontabRepos.add(parser.parseLine(line));
    }

    void loggingAtEachInvocation(String message) {
        boolean isEnabled = injector.getSchedulerConfig().enableLoggingForEachCrondInvocation;
        if (isEnabled) {
            log.info(message);
        }
    }

    public List<RawCrontabLine> getCurrentRawCrontabLines() {
        String serverName = null;
        try {
            serverName = CurrentServer.getServerName(injector
                    .getSchedulerConfig());
        } catch (Exception e) {
            log.error("Cannot get working server", e);
        }
        List<RawCrontabLine> currentRawContabLines = new ArrayList<RawCrontabLine>();
        for (Crontab crontab : crontabRepos.getCrontabLines()) {
            if (crontab.isServerToInvoke(serverName)) {
                StringBuilder sb = new StringBuilder();
                sb.append(crontab.commandClassName);
                sb.append(",");
                if (crontab.isIntervalInvocation) {
                    sb.append("interval:");
                    sb.append(crontab.intervalSeconds);
                    sb.append("sec");
                } else {
                    sb.append("next:");
                    sb.append(CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                            .getCalendar(crontab.nextInvocationTime)));
                }
                currentRawContabLines.add(new RawCrontabLine(sb.toString()));
            }
        }
        return currentRawContabLines;
    }

}
