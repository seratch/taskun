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
package com.github.seratch.taskun.util;

import java.util.Calendar;
import java.util.logging.Logger;

public final class CalendarUtil {

    protected static Logger log = Logger.getLogger(CalendarUtil.class.getCanonicalName());

    public static Calendar testData;

    public static Calendar getCurrentTime() {
        if (testData != null) {
            log.warning("Test data has been loaded...");
            Calendar result = deepCopy(testData);
            testData = null;
            return result;
        } else {
            return Calendar.getInstance();
        }
    }

    public static Calendar getCurrentTruncDate() {
        return dateTrunc(getCurrentTime());
    }

    public static Integer getYear(Calendar calendar) {
        Integer year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static Integer getMonth(Calendar calendar) {
        Integer month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    public static Integer getDay(Calendar calendar) {
        Integer day = calendar.get(Calendar.DATE);
        return day;
    }

    public static Integer getDayOfWeekNumber(Calendar cal) {
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static Integer get24Hour(Calendar calendar) {
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static Integer getMinute(Calendar calendar) {
        Integer minute = calendar.get(Calendar.MINUTE);
        return minute;
    }

    public static Integer getSecond(Calendar calendar) {
        Integer second = calendar.get(Calendar.SECOND);
        return second;
    }

    public static Integer getMillisecond(Calendar calendar) {
        Integer millisecond = calendar.get(Calendar.MILLISECOND);
        return millisecond;
    }

    public static Calendar getCalendar(String yyyy, String mm, String dd) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(yyyy));
        cal.set(Calendar.MONTH, Integer.valueOf(mm) - 1);
        cal.set(Calendar.DATE, Integer.valueOf(dd));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static Calendar getCalendar(int yyyy, int mm, int dd) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, yyyy);
        cal.set(Calendar.MONTH, mm - 1);
        cal.set(Calendar.DATE, dd);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public static Calendar getCalendar(String yyyy, String mm, String dd, String hh, String mi, String ss) {
        Calendar cal = getCalendar(yyyy, mm, dd);
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hh));
        cal.set(Calendar.MINUTE, Integer.valueOf(mi));
        cal.set(Calendar.SECOND, Integer.valueOf(ss));
        return cal;
    }

    public static Calendar getCalendar(int yyyy, int mm, int dd, int hh, int mi, int ss) {
        Calendar cal = getCalendar(yyyy, mm, dd);
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, mi);
        cal.set(Calendar.SECOND, ss);
        return cal;
    }

    public static Calendar dateTrunc(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        Calendar date = (Calendar) calendar.clone();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }

    public static boolean isFirstAfterSecond(Calendar first, Calendar second) {
        long firstValue = first.getTimeInMillis();
        long secondValue = second.getTimeInMillis();
        return (firstValue > secondValue) ? true : false;
    }

    public static Calendar deepCopy(Calendar src) {
        Calendar dest = Calendar.getInstance();
        dest.setTimeInMillis(src.getTimeInMillis());
        return dest;
    }

    public static Calendar addYears(Calendar src, int years) {
        Calendar dest = deepCopy(src);
        dest.add(Calendar.YEAR, years);
        return dest;
    }

    public static Calendar addMonths(Calendar src, int months) {
        Calendar dest = deepCopy(src);
        dest.add(Calendar.MONTH, months);
        return dest;
    }

    public static Calendar addDays(Calendar src, int days) {
        Calendar dest = deepCopy(src);
        dest.add(Calendar.DATE, days);
        return dest;
    }

    public static Calendar addHours(Calendar src, int hours) {
        Calendar dest = deepCopy(src);
        dest.add(Calendar.HOUR_OF_DAY, hours);
        return dest;
    }

    public static Calendar addMinutes(Calendar src, int minutes) {
        Calendar dest = deepCopy(src);
        dest.add(Calendar.MINUTE, minutes);
        return dest;
    }

    public static Calendar addSeconds(Calendar src, int seconds) {
        Calendar dest = deepCopy(src);
        dest.add(Calendar.SECOND, seconds);
        return dest;
    }

    public static Calendar getCalendar(long timeInMillis) {
        Calendar dest = Calendar.getInstance();
        dest.setTimeInMillis(timeInMillis);
        return dest;
    }

    public static String toYYYYMMDDHHMISS(Calendar calendar) {
        return String.format("%04d", getYear(calendar))
                + String.format("%02d", getMonth(calendar))
                + String.format("%02d", getDay(calendar))
                + String.format("%02d", get24Hour(calendar))
                + String.format("%02d", getMinute(calendar))
                + String.format("%02d", getSecond(calendar));
    }

}