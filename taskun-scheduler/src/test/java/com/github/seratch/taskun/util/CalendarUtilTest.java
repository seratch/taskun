package com.github.seratch.taskun.util;

import java.util.logging.Logger;
import com.github.seratch.taskun.util.CalendarUtil.*;
import static org.junit.Assert.*;
import org.junit.Test;

import junit.framework.TestCase;

import java.util.Calendar;

public class CalendarUtilTest {

    @Test 
	public void getYear_A$Calendar() throws Exception {
        Integer expected = 2009;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.YEAR, expected);
        Integer actual = CalendarUtil.getYear(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getYear_A$Calendar_1899() throws Exception {
        Integer expected = 1899;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.YEAR, expected);
        Integer actual = CalendarUtil.getYear(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getYear_A$Calendar_1900() throws Exception {
        Integer expected = 1900;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.YEAR, expected);
        Integer actual = CalendarUtil.getYear(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getYear_A$Calendar_2037() throws Exception {
        Integer expected = 2037;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.YEAR, expected);
        Integer actual = CalendarUtil.getYear(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getYear_A$Calendar_2038() throws Exception {
        Integer expected = 2038;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.YEAR, expected);
        Integer actual = CalendarUtil.getYear(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMonth_A$Calendar() throws Exception {
        Integer expected = 10;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MONTH, expected - 1);
        Integer actual = CalendarUtil.getMonth(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMonth_A$Calendar_jun0() throws Exception {
        Integer expected = 1;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MONTH, 0);
        Integer actual = CalendarUtil.getMonth(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMonth_A$Calendar_jun12() throws Exception {
        Integer expected = 1;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MONTH, 12);
        Integer actual = CalendarUtil.getMonth(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMonth_A$Calendar_dec() throws Exception {
        Integer expected = 12;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MONTH, 11);
        Integer actual = CalendarUtil.getMonth(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getDay_A$Calendar() throws Exception {
        Integer expected = 10;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.DATE, expected);
        Integer actual = CalendarUtil.getDay(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void get24Hour_A$Calendar() throws Exception {
        Integer expected = 23;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.HOUR_OF_DAY, expected);
        Integer actual = CalendarUtil.get24Hour(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void get24Hour_A$Calendar_0() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.HOUR_OF_DAY, expected);
        Integer actual = CalendarUtil.get24Hour(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void get24Hour_A$Calendar_24() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.HOUR_OF_DAY, 24);
        Integer actual = CalendarUtil.get24Hour(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMinute_A$Calendar() throws Exception {
        Integer expected = 59;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MINUTE, expected);
        Integer actual = CalendarUtil.getMinute(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMinute_A$Calendar_60() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MINUTE, 60);
        Integer actual = CalendarUtil.getMinute(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMinute_A$Calendar_0() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MINUTE, expected);
        Integer actual = CalendarUtil.getMinute(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getSecond_A$Calendar() throws Exception {
        Integer expected = 33;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.SECOND, expected);
        Integer actual = CalendarUtil.getSecond(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getSecond_A$Calendar_60() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.SECOND, 60);
        Integer actual = CalendarUtil.getSecond(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getSecond_A$Calendar_0() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.SECOND, expected);
        Integer actual = CalendarUtil.getSecond(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getCalendar_A$String$String$String() throws Exception {
        long expected = 1262185200000L;
        String arg0 = "2009";
        String arg1 = "12";
        String arg2 = "31";
        Calendar actual = CalendarUtil.getCalendar(arg0, arg1, arg2);
        assertEquals(expected, actual.getTimeInMillis());
    }

    @Test 
	public void getCalendar_A$String$String$String$String$String$String()
            throws Exception {
        long expected = 1262271599000L;
        String arg0 = "2009";
        String arg1 = "12";
        String arg2 = "31";
        String arg3 = "23";
        String arg4 = "59";
        String arg5 = "59";
        Calendar actual = CalendarUtil.getCalendar(arg0, arg1, arg2, arg3,
                arg4, arg5);
        assertEquals(expected, actual.getTimeInMillis());
    }

    @Test 
	public void dateTrunc_A$Calendar() throws Exception {
        Calendar today = CalendarUtil.dateTrunc(Calendar.getInstance());
        assertEquals(0, today.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, today.get(Calendar.MINUTE));
        assertEquals(0, today.get(Calendar.SECOND));
        assertEquals(0, today.get(Calendar.MILLISECOND));
    }

    @Test 
	public void getCurrentTime_A$() throws Exception {
        Calendar actual = CalendarUtil.getCurrentTime();
        assertNotNull(actual);
    }

    @Test 
	public void getCurrentTime_A$_testData() throws Exception {
        CalendarUtil.testData = Calendar.getInstance();
        Calendar actual = CalendarUtil.getCurrentTime();
        assertNotNull(actual);
        assertNull(CalendarUtil.testData);
    }

    @Test 
	public void getCurrentTruncDate_A$() throws Exception {
        Calendar actual = CalendarUtil.getCurrentTruncDate();
        assertEquals(0, actual.get(Calendar.HOUR));
        assertEquals(0, actual.get(Calendar.MINUTE));
        assertEquals(0, actual.get(Calendar.SECOND));
        assertEquals(0, actual.get(Calendar.MILLISECOND));
    }

    @Test 
	public void isFirstAfterSecond_A$Calendar$Calendar_true()
            throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "3", "1");
        Calendar arg1 = CalendarUtil.getCalendar("2010", "2", "28");
        // when
        boolean actual = CalendarUtil.isFirstAfterSecond(arg0, arg1);
        // then
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test 
	public void isFirstAfterSecond_A$Calendar$Calendar_false()
            throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "3", "1");
        Calendar arg1 = CalendarUtil.getCalendar("2010", "2", "28");
        // when
        boolean actual2 = CalendarUtil.isFirstAfterSecond(arg1, arg0);
        // then
        boolean expected2 = false;
        assertEquals(expected2, actual2);
    }

    @Test 
	public void isFirstAfterSecond_A$Calendar$Calendar_same()
            throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "3", "1");
        // when
        boolean actual3 = CalendarUtil.isFirstAfterSecond(arg0, arg0);
        // then
        boolean expected3 = false;
        assertEquals(expected3, actual3);
    }

    @Test 
	public void deepCopy_A$Calendar() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "3", "1");
        // when
        Calendar actual = CalendarUtil.deepCopy(arg0);
        // then
        actual.add(Calendar.SECOND, 1);
        assertNotSame(arg0, actual);
        assertTrue(CalendarUtil.isFirstAfterSecond(actual, arg0));
    }

    @Test 
	public void addYears_A$Calendar$int_plus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "3", "1");
        // when
        Calendar actual = CalendarUtil.addYears(arg0, 1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 2011, (int) CalendarUtil.getYear(actual));
    }

    @Test 
	public void addYears_A$Calendar$int_minus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "3", "1");
        // when
        Calendar actual = CalendarUtil.addYears(arg0, -1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 2009, (int) CalendarUtil.getYear(actual));
    }

    @Test 
	public void addMonths_A$Calendar$int_plus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1");
        // when
        Calendar actual = CalendarUtil.addMonths(arg0, 1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 3, (int) CalendarUtil.getMonth(actual));
    }

    @Test 
	public void addMonths_A$Calendar$int_minus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1");
        // when
        Calendar actual = CalendarUtil.addMonths(arg0, -1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 1, (int) CalendarUtil.getMonth(actual));
    }

    @Test 
	public void addDays_A$Calendar$int_plus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "28");
        // when
        Calendar actual = CalendarUtil.addDays(arg0, 1);
        // then
        assertEquals((int) 3, (int) CalendarUtil.getMonth(actual));
        assertEquals((int) 1, (int) CalendarUtil.getDay(actual));
    }

    @Test 
	public void addDays_A$Calendar$int_minus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "28");
        // when
        Calendar actual = CalendarUtil.addDays(arg0, -1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 27, (int) CalendarUtil.getDay(actual));
    }

    @Test 
	public void addHours_A$Calendar$int_plus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1", "22", "23",
                "34");
        // when
        Calendar actual = CalendarUtil.addHours(arg0, 1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 23, (int) CalendarUtil.get24Hour(actual));
    }

    @Test 
	public void addHours_A$Calendar$int_minus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1", "22", "23",
                "34");
        // when
        Calendar actual = CalendarUtil.addHours(arg0, -1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 21, (int) CalendarUtil.get24Hour(actual));
    }

    @Test 
	public void addMinutes_A$Calendar$int_plus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1", "22", "23",
                "34");
        // when
        Calendar actual = CalendarUtil.addMinutes(arg0, 1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 24, (int) CalendarUtil.getMinute(actual));
    }

    @Test 
	public void addMinutes_A$Calendar$int_minus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1", "22", "23",
                "34");
        // when
        Calendar actual = CalendarUtil.addMinutes(arg0, -1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 22, (int) CalendarUtil.getMinute(actual));
    }

    @Test 
	public void addSeconds_A$Calendar$int_plus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1", "22", "23",
                "34");
        // when
        Calendar actual = CalendarUtil.addSeconds(arg0, 1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 35, (int) CalendarUtil.getSecond(actual));
    }

    @Test 
	public void addSeconds_A$Calendar$int_minus() throws Exception {
        // given
        Calendar arg0 = CalendarUtil.getCalendar("2010", "2", "1", "22", "23",
                "34");
        // when
        Calendar actual = CalendarUtil.addSeconds(arg0, -1);
        // then
        assertNotSame(arg0.getTimeInMillis(), actual.getTimeInMillis());
        assertEquals((int) 33, (int) CalendarUtil.getSecond(actual));
    }

    @Test 
	public void getMillisecond_A$Calendar() throws Exception {
        Integer expected = 234;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MILLISECOND, expected);
        Integer actual = CalendarUtil.getMillisecond(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMillisecond_A$Calendar_999() throws Exception {
        Integer expected = 999;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MILLISECOND, expected);
        Integer actual = CalendarUtil.getMillisecond(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getMillisecond_A$Calendar_1000() throws Exception {
        Integer expected = 0;
        Calendar arg0 = Calendar.getInstance();
        arg0.set(Calendar.MILLISECOND, 1000);
        Integer actual = CalendarUtil.getMillisecond(arg0);
        assertEquals(expected, actual);
    }

    @Test 
	public void getCalendar_A$int$int$int() throws Exception {
        Calendar actual = CalendarUtil.getCalendar(2000, 2, 3);
        assertTrue(2000 == actual.get(Calendar.YEAR));
        assertTrue(1 == actual.get(Calendar.MONTH));
        assertTrue(3 == actual.get(Calendar.DATE));
    }

    @Test 
	public void getCalendar_A$int$int$int$int$int$int() throws Exception {
        Calendar actual = CalendarUtil.getCalendar(2000, 2, 3, 4, 5, 6);
        assertTrue(2000 == actual.get(Calendar.YEAR));
        assertTrue(1 == actual.get(Calendar.MONTH));
        assertTrue(3 == actual.get(Calendar.DATE));
        assertTrue(4 == actual.get(Calendar.HOUR));
        assertTrue(5 == actual.get(Calendar.MINUTE));
        assertTrue(6 == actual.get(Calendar.SECOND));
    }

    @Test 
	public void getDayOfWeekNumber_A$Calendar() throws Exception {
        assertEquals((int) 1,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "18")));
        assertEquals((int) 2,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "19")));
        assertEquals((int) 3,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "20")));
        assertEquals((int) 4,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "21")));
        assertEquals((int) 5,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "22")));
        assertEquals((int) 6,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "23")));
        assertEquals((int) 7,
                (int) CalendarUtil.getDayOfWeekNumber(CalendarUtil.getCalendar(
                        "2010", "4", "24")));
    }

    @Test 
	public void getCalendar_A$long() throws Exception {
        long arg0 = 12345L;
        Calendar actual = CalendarUtil.getCalendar(arg0);
        assertEquals(arg0, actual.getTimeInMillis());
    }

    @Test 
	public void toYYYYMMDDHHMISS_A$Calendar() throws Exception {
        Calendar arg0 = CalendarUtil.getCalendar("2010", "02", "3", "1", "2",
                "3");
        String actual = CalendarUtil.toYYYYMMDDHHMISS(arg0);
        String expected = "20100203010203";
        assertEquals(expected, actual);
    }

	@Test
	public void type() throws Exception {
		assertNotNull(CalendarUtil.class);
	}

	@Test
	public void instantiation() throws Exception {
		CalendarUtil target = new CalendarUtil();
		assertNotNull(target);
	}

}
