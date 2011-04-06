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

import com.github.seratch.taskun.logging.TaskunLog;
import com.github.seratch.taskun.logging.TaskunLogUtilLoggerImpl;
import com.github.seratch.taskun.util.CalendarUtil;
import com.github.seratch.taskun.util.StringUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CrontabParser {

    public CrontabParser(Class<? extends TaskunLog> logImplClass) {
        this.logImplClass = logImplClass;
    }

    private Class<? extends TaskunLog> logImplClass = TaskunLogUtilLoggerImpl.class;

    private TaskunLog taskunLog = getLog(CronDaemon.class.getCanonicalName());

    TaskunLog getLog() {
        return getLog(CronDaemon.class.getCanonicalName());
    }

    TaskunLog getLog(String name) {
        try {
            return logImplClass.getConstructor(String.class).newInstance(name);
        } catch (Throwable t) {
            return new TaskunLogUtilLoggerImpl(name);
        }
    }

    public Crontab parseLine(RawCrontabLine line) {
        Crontab crontab = new Crontab();
        String lineString = line.toString();
        if (lineString.equals("") || lineString.startsWith("#")) {
            return null;
        }
        crontab.rawLine = new RawCrontabLine(lineString);
        try {
            String[] elements = lineString.split("\\s+");
            if (elements[0].matches("interval:\\d+?sec")) {
                return getIntervalInvocationCrontab(crontab, elements);
            } else {
                return getBasicCrontab(crontab, elements);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            taskunLog.error("Invalid crontab expression...");
            throw new IllegalArgumentException("Invalid crontab expression...");
        }
    }

    public List<Integer> getFixedInvocations(int maxValue, CrontabElement crontabElement) {
        String element = crontabElement.toString();
        int begin = 0;
        int period = 1;
        int end = maxValue;
        List<Integer> invocations = new ArrayList<Integer>();
        String[] slashDelimited = element.split("/");
        if (element.equals("*")) {
            for (int i = 0; i <= maxValue; i++) {
                invocations.add(i);
            }
            return invocations;
        } else if (slashDelimited.length == 1 && element.matches("^[\\d,]+$")) {
            String[] commaDelimited = element.split(",");
            int len = commaDelimited.length;
            for (int i = 0; i < len; i++) {
                invocations.add(Integer.valueOf(commaDelimited[i]));
            }
            return invocations;
        } else if (slashDelimited.length == 2) {
            String leftSide = slashDelimited[0];
            if (leftSide.matches("^\\d+$")) {
                begin = Integer.valueOf(slashDelimited[0]);
            } else if (leftSide.matches("^\\d+?-\\d+$")) {
                String[] beginAndEnd = slashDelimited[0].split("-");
                begin = Integer.valueOf(beginAndEnd[0]);
                end = Integer.valueOf(beginAndEnd[1]);
            }
            period = Integer.valueOf(slashDelimited[1]);
        } else {
            throw new IllegalArgumentException(
                    "Invalid crontab configuration value:" + element);
        }
        int invocation = begin;
        while (invocation <= end) {
            invocations.add(invocation);
            invocation += period;
        }
        return invocations;
    }

    public long getNextInvocationTime(Calendar currentTime, Crontab crontab) {
        int second = CalendarUtil.getSecond(currentTime);
        int minute = CalendarUtil.getMinute(currentTime);
        if (second > 0) {
            minute++;
        }
        if (crontab.minuteElement.isFixedValue()) {
            minute = getFixedNextInvocationValue(minute, 59, crontab.minuteElement);
        } else {
            minute = getFixedSameOrNextInvocationValue(minute, crontab.minuteFixedInvocations);
        }
        int hour = CalendarUtil.get24Hour(currentTime);
        if (crontab.hourElement.isFixedValue()) {
            int newHour = crontab.hourElement.toInt();
            if (newHour > hour) {
                if (crontab.minuteElement.isFixedValue()) {
                    minute = getFixedNextInvocationValue(minute, 59, crontab.minuteElement);
                } else {
                    minute = getFixedMinimumInvocationValue(crontab.minuteFixedInvocations);
                }
            }
            hour = newHour;
        }
        crontab = replaceWildcardsOfMinuteOrHourValue(crontab);
        int day = CalendarUtil.getDay(currentTime);
        if (crontab.dayElement.isFixedValue()) {
            day = crontab.dayElement.toInt();
        }
        int dayOfWeek = getCrontabDayOfWeekNumber(currentTime);
        if (dayOfWeek >= 7) {
            dayOfWeek = dayOfWeek - 7;
        }
        int month = CalendarUtil.getMonth(currentTime);
        if (crontab.monthElement.isFixedValue()) {
            month = crontab.monthElement.toInt();
        }
        int year = CalendarUtil.getYear(currentTime);
        // next invocation millis
        Calendar nextInvocationTime = CalendarUtil.getCalendar(
                year, month, day, hour, minute, 0);

        boolean isMinuteModified = false;
        boolean isHourModified = false;
        boolean isDayModified = false;
        boolean isMonthModified = false;

        boolean isDayOfWeekPrior = true;

        int loopCounter = 0;

        // dayElement of week type
        if (crontab.dayOfWeekElement.isNotWildcard()) {
            // 1. next invocation time in millis is larger than current time
            // or
            // 2. dayElement of week value to invoke next is defined one in crontab
            while (nextInvocationTime.getTimeInMillis() <= currentTime.getTimeInMillis()
                    || (isDayOfWeekPrior && isDayOfWeekNotCameAt(crontab, nextInvocationTime, dayOfWeek))) {
                if (loopCounter >= 100) {
                    throw new IllegalStateException(
                            "Cannot get next invocation time of crontab scheduled task...");
                }
                if (isMinuteModified || crontab.minuteElement.isWildcard()) {
                    if (isHourModified || crontab.hourElement.isNotWildcard()) {
                        if (isHourModified || crontab.hourElement.isFixedValue()) {
                            if (isDayModified) {
                                if (isMonthModified || crontab.monthElement.isNotWildcard()) {
                                    if (isMonthModified || crontab.monthElement.isFixedValue()) {
                                        // cannot specify year at crontab
                                        nextInvocationTime.add(Calendar.YEAR, 1);
                                    } else {
                                        nextInvocationTime.set(Calendar.MONTH,
                                                getFixedNextInvocationValue(
                                                        month, 12, crontab.monthElement));
                                        isMonthModified = true;
                                    }
                                } else {
                                    nextInvocationTime.add(Calendar.MONTH, 1);
                                    isMonthModified = true;
                                }
                            } else {
                                boolean isDayOfWeekNotContainedInCrontab =
                                        !isInvocationValueContainedInCrontabValue(
                                                dayOfWeek, 6, crontab.dayOfWeekElement);
                                boolean isNextInvocationNotInThisDate =
                                        currentTime.getTimeInMillis() > nextInvocationTime.getTimeInMillis();
                                if (isDayOfWeekNotContainedInCrontab || isNextInvocationNotInThisDate) {
                                    // add days until next dayElement of week
                                    int addDays = 0;
                                    int nextDayOfWeek = getFixedNextInvocationValue(
                                            dayOfWeek, 6, crontab.dayOfWeekElement);
                                    if (nextDayOfWeek > dayOfWeek) {
                                        addDays = nextDayOfWeek - dayOfWeek;
                                    } else {
                                        addDays = nextDayOfWeek - dayOfWeek + 7;
                                    }
                                    // day element
                                    int addDaysByDayElement = getFixedNextInvocationValue(day,
                                            crontab.dayFixedInvocations) - day;
                                    if (addDaysByDayElement > 0 && addDaysByDayElement < addDays) {
                                        addDays = addDaysByDayElement;
                                        isDayOfWeekPrior = false;
                                    }
                                    nextInvocationTime.add(Calendar.DATE, addDays);
                                }
                                isDayModified = true;
                            }
                        } else {
                            nextInvocationTime.set(Calendar.HOUR_OF_DAY,
                                    getFixedNextInvocationValue(hour, 23, crontab.hourElement));
                            isHourModified = true;
                        }
                    } else {
                        nextInvocationTime.add(Calendar.HOUR_OF_DAY, 1);
                        isHourModified = true;
                    }
                } else {
                    if (!isDayModified && hour >= CalendarUtil.get24Hour(currentTime)) {
                        nextInvocationTime.set(Calendar.MINUTE,
                                getFixedSameOrNextInvocationValue(minute, crontab.minuteFixedInvocations));
                    } else {
                        nextInvocationTime.set(Calendar.MINUTE,
                                getFixedMinimumInvocationValue(crontab.minuteFixedInvocations));
                    }
                    isMinuteModified = true;
                }
                loopCounter++;
            }
        } else {
            // check next invocation timestamp
            while ((isMonthModified && (!isDayModified || !isHourModified))
                    || nextInvocationTime.getTimeInMillis() < currentTime
                    .getTimeInMillis()) {
                if (loopCounter >= 100) {
                    throw new IllegalStateException(
                            "Cannot get next invocation time of crontab scheduled task...");
                }
                if (isHourModified || crontab.hourElement.isNotWildcard()) {
                    if (isHourModified || crontab.hourElement.isFixedValue()) {
                        if (isDayModified || crontab.dayElement.isNotWildcard()) {
                            if (isDayModified || crontab.dayElement.isFixedValue()) {
                                if (isMonthModified || crontab.monthElement.isNotWildcard()) {
                                    if (isMonthModified || crontab.monthElement.isFixedValue()) {
                                        // cannot specify year at crontab
                                        nextInvocationTime.add(Calendar.YEAR, 1);
                                    } else {
                                        nextInvocationTime.set(Calendar.MONTH,
                                                getFixedNextInvocationValue(
                                                        month, 12, crontab.monthElement));
                                        isHourModified = true;
                                        isDayModified = true;
                                        isMonthModified = true;
                                    }
                                } else {
                                    nextInvocationTime.add(Calendar.MONTH, 1);
                                    isHourModified = false;
                                    isDayModified = crontab.dayElement.isFixedValue();
                                    isMonthModified = true;
                                    // renew hourElement
                                    hour = 0;
                                    if (isInvocationValueContainedInCrontabValue(0, 23, crontab.hourElement)) {
                                        nextInvocationTime.set(Calendar.HOUR_OF_DAY, 0);
                                        isHourModified = true;
                                    }
                                    // renew minuteElement
                                    minute = 0;
                                    int newMinuteValue = 0;
                                    if (crontab.minuteElement.isFixedValue()) {
                                        newMinuteValue = getFixedNextInvocationValue(
                                                minute, 59, crontab.minuteElement);
                                    } else {
                                        newMinuteValue = getFixedNextInvocationValue(
                                                minute, crontab.minuteFixedInvocations);
                                    }
                                    nextInvocationTime.set(Calendar.MINUTE, newMinuteValue);
                                    // renew second
                                    nextInvocationTime.set(Calendar.SECOND, 0);
                                }
                            } else {
                                nextInvocationTime.set(Calendar.DATE,
                                        getFixedNextInvocationValue(day, 31, crontab.dayElement));
                                isHourModified = true;
                                isDayModified = true;
                            }
                        } else {
                            nextInvocationTime.add(Calendar.DATE, 1);
                            isHourModified = true;
                            isDayModified = true;
                        }
                    } else {
                        nextInvocationTime.set(Calendar.HOUR_OF_DAY,
                                getFixedNextInvocationValue(hour, 23, crontab.hourElement));
                        isHourModified = true;
                    }
                } else {
                    nextInvocationTime.add(Calendar.HOUR_OF_DAY, 1);
                    isHourModified = true;
                }
                loopCounter++;
            }
        }
        return nextInvocationTime.getTimeInMillis();
    }

    public Crontab replaceWildcardsOfMinuteOrHourValue(Crontab crontab) {
        if (crontab.minuteElement.isWildcard()) {
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            for (int i = 1; i < 60; i++) {
                sb.append(",");
                sb.append(i);
            }
            crontab.minuteElement = new CrontabElement(sb.toString());
            crontab.minuteFixedInvocations = getFixedInvocations(59,
                    crontab.minuteElement);
        }
        if (crontab.hourElement.isWildcard()) {
            StringBuilder sb = new StringBuilder();
            sb.append("0");
            for (int i = 1; i < 24; i++) {
                sb.append(",");
                sb.append(i);
            }
            crontab.hourElement = new CrontabElement(sb.toString());
            crontab.hourFixedInvocations = getFixedInvocations(23, crontab.hourElement);
        }
        return crontab;
    }

    public int getCrontabDayOfWeekNumber(Calendar cal) {
        return CalendarUtil.getDayOfWeekNumber(cal) - 1;
    }

    public int getFixedMinimumInvocationValue(List<Integer> fixedInvocations) {
        int dest = 60;
        if (fixedInvocations == null || fixedInvocations.size() == 0) {
            return 0;
        }
        for (int each : fixedInvocations) {
            if (dest > each)
                dest = each;
        }
        return dest;
    }

    public int getFixedSameOrNextInvocationValue(int currentValue, List<Integer> fixedInvocations) {
        if (fixedInvocations != null) {
            if (fixedInvocations.size() == 0) {
                throw new IllegalStateException("Invalid configuration...");
            }
            for (int fixed : fixedInvocations) {
                if (fixed >= currentValue) {
                    return fixed;
                }
            }
            return fixedInvocations.get(0);
        } else {
            return currentValue;
        }
    }

    public int getFixedNextInvocationValue(int currentValue, List<Integer> fixedInvocations) {
        if (fixedInvocations != null) {
            if (fixedInvocations.size() == 0) {
                throw new IllegalStateException("Invalid configuration...");
            }
            for (int fixed : fixedInvocations) {
                if (fixed > currentValue) {
                    return fixed;
                }
            }
            return fixedInvocations.get(0);
        } else {
            return currentValue;
        }
    }

    public int getFixedNextInvocationValue(int currentValue, int maxValue, CrontabElement element) {
        String elementValue = element.toString();
        if (elementValue.matches("^\\d+$")) {
            return Integer.valueOf(elementValue);
        } else if (elementValue.contains("/")
                || elementValue.matches("^[\\d,]+$")) {
            List<Integer> fixedInvocations = getFixedInvocations(maxValue,
                    element);
            for (int fixed : fixedInvocations) {
                if (fixed > currentValue) {
                    return fixed;
                }
            }
            return fixedInvocations.get(0);
        } else {
            throw new IllegalArgumentException(
                    "Invalid crontab configuration value:" + elementValue);
        }
    }

    public boolean isInvocationValueContainedInCrontabValue(
            int invocationValue, int maxValue, CrontabElement element) {
        String elementValue = element.toString();
        if (elementValue.matches("^\\d+$")) {
            return invocationValue == Integer.valueOf(elementValue);
        } else if (elementValue.contains("/")
                || elementValue.matches("^[\\d,]+$")) {
            List<Integer> fixedInvocations = getFixedInvocations(maxValue,
                    element);
            for (int fixed : fixedInvocations) {
                if (invocationValue == fixed) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public Crontab getIntervalInvocationCrontab(Crontab crontab, String[] elements) {
        crontab.isIntervalInvocation = true;
        crontab.intervalSeconds = getLongValue(elements[0].split(":")[1]);
        crontab.initialIntervalSeconds = getLongValue(elements[1].split(":")[1]);
        crontab.commandClassName = getCommandClassNameElement(elements[2]);
        crontab.multiplicity = getMultiplicityOfCommandWorkers(elements[2]);
        if (elements.length == 4 && !StringUtil.isEmpty(elements[3])) {
            String[] servers = elements[3].split(",");
            crontab.namedServers = new ArrayList<String>();
            for (String server : servers) {
                if (!StringUtil.isEmpty(server)) {
                    crontab.namedServers.add(server);
                }
            }
        }
        return crontab;
    }

    long getLongValue(String number) {
        return Long.valueOf(number.replaceAll("[^\\d]+", ""));
    }

    long getMultiplicityOfCommandWorkers(String element) {
        String[] arr = element.split("\\*");
        if (arr.length == 2) {
            return Long.valueOf(arr[1]);
        } else {
            return 1L;
        }
    }

    CrontabCommandClassNameElement getCommandClassNameElement(String element) {
        return new CrontabCommandClassNameElement(element.split("\\*")[0]);
    }

    Crontab getBasicCrontab(Crontab crontab, String[] elements) {
        crontab.minuteElement = new CrontabElement(elements[0]);
        crontab.minuteFixedInvocations = getFixedInvocations(59, crontab.minuteElement);
        crontab.hourElement = new CrontabElement(elements[1]);
        crontab.hourFixedInvocations = getFixedInvocations(23, crontab.hourElement);
        crontab = replaceWildcardsOfMinuteOrHourValue(crontab);
        crontab.dayElement = new CrontabElement(elements[2]);
        if (crontab.dayElement.isNotWildcard()) {
            crontab.dayFixedInvocations = getFixedInvocations(31, crontab.dayElement);
        }
        crontab.monthElement = new CrontabElement(elements[3]);
        if (crontab.monthElement.isNotWildcard()) {
            crontab.monthFixedInvocations = getFixedInvocations(12,
                    crontab.monthElement);
        }
        crontab.dayOfWeekElement = new CrontabElement(elements[4]);
        if (crontab.dayOfWeekElement.isNotWildcard()) {
            crontab.dayOfWeekFixedInvocations = getFixedInvocations(7,
                    crontab.dayOfWeekElement);
        }
        crontab.commandClassName = getCommandClassNameElement(elements[5]);
        crontab.multiplicity = getMultiplicityOfCommandWorkers(elements[5]);
        crontab.nextInvocationTime = getNextInvocationTime(
                CalendarUtil.getCurrentTime(), crontab);
        if (elements.length == 7 && !StringUtil.isEmpty(elements[6])) {
            String[] servers = elements[6].split(",");
            crontab.namedServers = new ArrayList<String>();
            for (String server : servers) {
                if (!StringUtil.isEmpty(server)) {
                    crontab.namedServers.add(server);
                }
            }
        }
        return crontab;
    }

    boolean isDayOfWeekNotCameAt(Crontab crontab, Calendar nextInvocationTime, int dayOfWeek) {
        return getCrontabDayOfWeekNumber(nextInvocationTime)
                != getFixedNextInvocationValue(dayOfWeek, 6, crontab.dayOfWeekElement)
                &&
                !isInvocationValueContainedInCrontabValue(
                        getCrontabDayOfWeekNumber(nextInvocationTime), 6, crontab.dayOfWeekElement);
    }


}
