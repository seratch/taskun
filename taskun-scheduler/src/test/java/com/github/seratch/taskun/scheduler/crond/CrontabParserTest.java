package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.logging.TaskunLogUtilLoggerImpl;
import com.github.seratch.taskun.util.CalendarUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CrontabParserTest {

    CrontabParser parser = new CrontabParser(TaskunLogUtilLoggerImpl.class);

    @Test
    public void parseLine_A$Crontab_noClassName() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine("*/3 * * * * ");
        try {
            parser.parseLine(arg0);
            fail("Exception did not occurred.");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void parseLine_A$Crontab_readable() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine(
                "*/3 */2 * * * hoge.foo.SampleTask host1,host2");
        long current = CalendarUtil.getCurrentTime().getTimeInMillis();
        Crontab actual = parser.parseLine(arg0);
        assertEquals(arg0, actual.rawLine);
        assertEquals("*/3", actual.minuteElement.toString());
        assertEquals(20, actual.minuteFixedInvocations.size());
        assertEquals("*/2", actual.hourElement.toString());
        assertEquals(12, actual.hourFixedInvocations.size());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(2, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(current < actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_space() throws Exception {
        String[] arg = new String[]{"*/3 * * * * hoge.foo.SampleTask",
                "*/3 * * * * hoge.foo.SampleTask",
                "*/3	*	 *	*	 	*			hoge.foo.SampleTask",
                "*/3   *     *  *  *            hoge.foo.SampleTask",
                "        */3  *  * * * hoge.foo.SampleTask",
                "*/3 * * * * hoge.foo.SampleTask",};
        int argLen = arg.length;
        for (int i = 0; i < argLen; i++) {
            Crontab actual = parser.parseLine(new RawCrontabLine(arg[i]));
            assertEquals("*/3", actual.minuteElement.toString());
            assertEquals(
                    "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                    actual.hourElement.toString());
            assertEquals("*", actual.dayElement.toString());
            assertEquals("*", actual.monthElement.toString());
            assertEquals("*", actual.dayOfWeekElement.toString());
            assertEquals("hoge.foo.SampleTask",
                    actual.commandClassName.toString());
        }
    }

    @Test
    public void parseLine_A$Crontab_beginEnd() throws Exception {
        String arg0 = "1-20/3 * * * * hoge.foo.SampleTask";
        long current = CalendarUtil.getCurrentTime().getTimeInMillis();
        Crontab actual = parser.parseLine(new RawCrontabLine(arg0));
        assertEquals(arg0, actual.rawLine.toString());
        assertEquals("1-20/3", actual.minuteElement.toString());
        assertEquals(7, actual.minuteFixedInvocations.size());
        assertTrue(1 == actual.minuteFixedInvocations.get(0));
        assertTrue(4 == actual.minuteFixedInvocations.get(1));
        assertTrue(7 == actual.minuteFixedInvocations.get(2));
        assertTrue(10 == actual.minuteFixedInvocations.get(3));
        assertTrue(13 == actual.minuteFixedInvocations.get(4));
        assertTrue(16 == actual.minuteFixedInvocations.get(5));
        assertTrue(19 == actual.minuteFixedInvocations.get(6));
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(current < actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_fixedTime() throws Exception {
        String arg0 = "0 5 * * * hoge.foo.SampleTask host1,host2";
        long current = CalendarUtil.getCurrentTime().getTimeInMillis();
        Crontab actual = parser.parseLine(new RawCrontabLine(arg0));
        assertEquals(arg0, actual.rawLine.toString());
        assertEquals("0", actual.minuteElement.toString());
        assertEquals("5", actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(2, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(current < actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_minuteInterval1() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "*/1 * * * * hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "33", "10");
        long expected = CalendarUtil.getCalendar("2010", "4", "21", "14", "34",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("*/1", actual.minuteElement.toString());
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expected == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_minuteInterval1_59() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "*/1 * * * * hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "59", "10");
        long expceted = CalendarUtil.getCalendar("2010", "4", "21", "15", "00",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("*/1", actual.minuteElement.toString());
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expceted == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_minuteInterval3() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "*/3 * * * * hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "33", "10");
        long expceted = CalendarUtil.getCalendar("2010", "4", "21", "14", "36",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("*/3", actual.minuteElement.toString());
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expceted == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_minuteInterval3_59() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "*/3 * * * * hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "59", "00");
        long expceted = CalendarUtil.getCalendar("2010", "4", "21", "15", "00",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("*/3", actual.minuteElement.toString());
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expceted == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_minuteInterval3_59_start2() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "2/3 * * * * hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "59", "10");
        long expceted = CalendarUtil.getCalendar("2010", "4", "21", "15", "02",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("2/3", actual.minuteElement.toString());
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expceted == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_hourInterval3() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "25 */3 * * *  hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "33", "00");
        long expceted = CalendarUtil.getCalendar("2010", "4", "21", "15", "25",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("25", actual.minuteElement.toString());
        assertEquals("*/3", actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expceted == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_severalDays() throws Exception {
        RawCrontabLine crontab = new RawCrontabLine(
                "0 2 20,22 * *  hoge.foo.SampleTask server1");
        CalendarUtil.testData = CalendarUtil.getCalendar("2010", "4", "21",
                "14", "33", "00");
        long expceted = CalendarUtil.getCalendar("2010", "4", "22", "2", "0",
                "00").getTimeInMillis();
        Crontab actual = parser.parseLine(crontab);
        assertEquals(crontab, actual.rawLine);
        assertEquals("0", actual.minuteElement.toString());
        assertEquals("2", actual.hourElement.toString());
        assertEquals("20,22", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(1, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new Date(actual.nextInvocationTime).toString(),
                expceted == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_fixedTimeNextInvocation() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine(
                "0 5 2 4 * hoge.foo.SampleTask host1,host2");
        Calendar current = CalendarUtil.getCalendar("2010", "4", "1", "0", "0",
                "0");
        CalendarUtil.testData = current;
        Crontab actual = parser.parseLine(arg0);
        assertEquals(arg0, actual.rawLine);
        assertEquals("0", actual.minuteElement.toString());
        assertEquals("5", actual.hourElement.toString());
        assertEquals("2", actual.dayElement.toString());
        assertEquals("4", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(2, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        long expectedNextInvocationTime = CalendarUtil.getCalendar("2010", "4",
                "2", "5", "0", "0").getTimeInMillis();
        assertTrue(
                expectedNextInvocationTime + "," + actual.nextInvocationTime,
                expectedNextInvocationTime == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_fixedTimeNextInvocation2() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine(
                "0 5 25 * * hoge.foo.SampleTask host1,host2");
        Calendar current = CalendarUtil.getCalendar("2010", "4", "1", "0", "0",
                "0");
        CalendarUtil.testData = current;
        Crontab actual = parser.parseLine(arg0);
        assertEquals(arg0, actual.rawLine);
        assertEquals("0", actual.minuteElement.toString());
        assertEquals("5", actual.hourElement.toString());
        assertEquals("25", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals(2, actual.namedServers.size());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        long expectedNextInvocationTime = CalendarUtil.getCalendar("2010", "4",
                "25", "5", "0", "0").getTimeInMillis();
        assertTrue(
                expectedNextInvocationTime + "," + actual.nextInvocationTime,
                expectedNextInvocationTime == actual.nextInvocationTime);
    }

    @Test
    public void parseLine_A$Crontab_commaSeperated() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine(
                "1,2,3 * * * * hoge.foo.SampleTask");
        long current = CalendarUtil.getCurrentTime().getTimeInMillis();
        Crontab actual = parser.parseLine(arg0);
        assertEquals(arg0, actual.rawLine);
        assertEquals("1,2,3", actual.minuteElement.toString());
        assertEquals(3, actual.minuteFixedInvocations.size());
        assertTrue(1 == actual.minuteFixedInvocations.get(0));
        assertTrue(2 == actual.minuteFixedInvocations.get(1));
        assertTrue(3 == actual.minuteFixedInvocations.get(2));
        assertEquals(
                "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23",
                actual.hourElement.toString());
        assertEquals("*", actual.dayElement.toString());
        assertEquals("*", actual.monthElement.toString());
        assertEquals("*", actual.dayOfWeekElement.toString());
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(current < actual.nextInvocationTime);
    }

    @Test
    public void getFixedInvocations_A$int$RawCrontabLine() throws Exception {
        int arg0 = 59;
        CrontabElement arg1 = new CrontabElement("0/5");
        List<Integer> actual = parser.getFixedInvocations(arg0, arg1);
        assertTrue(actual.size() == 12);
        assertTrue(0 == actual.get(0));
        assertTrue(5 == actual.get(1));
        assertTrue(10 == actual.get(2));
        assertTrue(15 == actual.get(3));
        assertTrue(20 == actual.get(4));
        assertTrue(25 == actual.get(5));
        assertTrue(30 == actual.get(6));
        assertTrue(35 == actual.get(7));
        assertTrue(40 == actual.get(8));
        assertTrue(45 == actual.get(9));
        assertTrue(50 == actual.get(10));
        assertTrue(55 == actual.get(11));
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab()
            throws Exception {
        Calendar arg0 = CalendarUtil.getCurrentTime();
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("*/3");
        crontab.minuteFixedInvocations = new ArrayList<Integer>();
        crontab.minuteFixedInvocations.add(1);
        crontab.hourElement = new CrontabElement("*");
        crontab.dayElement = new CrontabElement("*");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("*");
        long actual = parser.getNextInvocationTime(arg0, crontab);
        assertTrue(arg0.getTimeInMillis() < actual);
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_fixedInvocationAndRenew()
            throws Exception {
        Calendar arg0 = CalendarUtil.getCalendar("2010", "4", "21", "22", "00",
                "01");
        Calendar expected = CalendarUtil.getCalendar("2010", "4", "22", "5",
                "00", "00");
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0");
        crontab.hourElement = new CrontabElement("5,11,22");
        crontab.hourFixedInvocations = new ArrayList<Integer>();
        crontab.hourFixedInvocations.add(5);
        crontab.hourFixedInvocations.add(11);
        crontab.hourFixedInvocations.add(22);
        crontab.dayElement = new CrontabElement("*");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("*");
        long actual = parser.getNextInvocationTime(arg0, crontab);
        assertTrue(expected.getTimeInMillis() == actual);
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_dayOfWeek()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0");
        crontab.hourElement = new CrontabElement("9");
        crontab.dayElement = new CrontabElement("*");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("1");

        assertTrue(CalendarUtil.getCalendar("2010", "6", "14", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "12", "2", "00", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "6", "14", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "13", "2", "00", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "6", "14", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "13", "23", "00", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "6", "14", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "14", "8", "20", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "6", "21", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "14", "9", "20", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "6", "21", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "15", "2", "20", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "6", "21", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "20", "23", "20", "01"),
                crontab));
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_dayOfWeek_several()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0");
        crontab.hourElement = new CrontabElement("9");
        crontab.dayElement = new CrontabElement("*");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("1,2,3,4,5");
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "2", "00", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "9", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "23", "00", "01"),
                crontab));
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_dayOfWeek_several_with_day()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0");
        crontab.hourElement = new CrontabElement("9");
        crontab.dayElement = new CrontabElement("6,7,9,10");
        crontab.dayFixedInvocations = parser.getFixedInvocations(31, crontab.dayElement);
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("1,2,3,4,5");
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "9", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "2", "00", "01"),
                crontab));
        // compliant with the specific of crond
        // specified day element is prior than day of week element
        String result = CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                .getCalendar(parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "8", "6", "23", "00", "01"),
                        crontab)));
        assertTrue(result,
                CalendarUtil.getCalendar("2010", "8", "7", "9", "00", "00")
                        .getTimeInMillis() == parser.getNextInvocationTime(
                        CalendarUtil.getCalendar("2010", "8", "6", "23", "00", "01"), crontab));
        long actual = parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "7", "23", "00", "01"),
                crontab);
        assertTrue(CalendarUtil.getCalendar("2010", "8", "9", "9", "00", "00")
                .getTimeInMillis() == actual);
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_dayOfWeek_several_with_hour()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0");
        crontab.hourElement = new CrontabElement("10,17");
        crontab.dayElement = new CrontabElement("*");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("1,2,3,4,5");
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "10", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "2", "00", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "17", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "10", "01", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "17", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "15", "01", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "9", "10", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "17", "10", "00"),
                crontab));
        assertTrue(CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                .getCalendar(parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "8", "6", "23", "00", "01"),
                        crontab))),
                CalendarUtil.getCalendar("2010", "8", "9", "10", "00", "00")
                        .getTimeInMillis() == parser.getNextInvocationTime(
                        CalendarUtil.getCalendar("2010", "8", "6", "23", "00",
                                "01"), crontab));
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_dayOfWeek_several_with_minute()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0,30");
        crontab.minuteFixedInvocations = parser.getFixedInvocations(59, crontab.minuteElement);
        crontab.hourElement = new CrontabElement("10");
        crontab.dayElement = new CrontabElement("*");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("1,2,3,4,5");
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "10", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "2", "00", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "6", "10", "30", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "10", "10", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "9", "10", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "10", "50", "01"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "8", "9", "10", "00", "00")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "6", "17", "10", "00"),
                crontab));
        // 0,30 10 * * 1,2,3,4,5
        // current:  2010/08/07 03:20:00
        // expected: 2010/08/09 10:00:00
        // actual:   2010/08/09 10:30:00
        String result = CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil.getCalendar(
                parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "8", "7", "3", "20", "00"),
                        crontab)));
        long actual = parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "8", "7", "3", "20", "00"),
                crontab);
        assertTrue(result,
                CalendarUtil.getCalendar("2010", "8", "9", "10", "00", "00").getTimeInMillis()
                        == actual);
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_monthly1()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("0");
        crontab.hourElement = new CrontabElement("0,1,2,3");
        crontab.dayElement = new CrontabElement("1");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("*");
        assertTrue(CalendarUtil.getCalendar("2010", "7", "1", "0", "0", "0")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "6", "30", "20", "00", "00"),
                crontab));
        assertTrue(CalendarUtil.getCalendar("2010", "7", "1", "1", "0", "0")
                .getTimeInMillis() == parser.getNextInvocationTime(
                CalendarUtil.getCalendar("2010", "7", "1", "0", "12", "34"),
                crontab));
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_monthly2()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("25");
        crontab.hourElement = new CrontabElement("1,2,3");
        crontab.dayElement = new CrontabElement("1");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("*");
        assertTrue(CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                .getCalendar(parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "6", "30", "20", "00", "00"),
                        crontab))),
                CalendarUtil.getCalendar("2010", "7", "1", "1", "25", "0")
                        .getTimeInMillis() == parser.getNextInvocationTime(
                        CalendarUtil.getCalendar("2010", "6", "30", "20", "00",
                                "00"), crontab));
        assertTrue(CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                .getCalendar(parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "7", "1", "1", "42", "34"),
                        crontab))),
                CalendarUtil.getCalendar("2010", "7", "1", "2", "25", "0")
                        .getTimeInMillis() == parser.getNextInvocationTime(
                        CalendarUtil.getCalendar("2010", "7", "1", "1", "42",
                                "34"), crontab));
    }

    @Test
    public void getNextInvocationTime_A$Calendar$Crontab_monthly3()
            throws Exception {
        Crontab crontab = new Crontab();
        crontab.minuteElement = new CrontabElement("25");
        crontab.hourElement = new CrontabElement("*");
        crontab.dayElement = new CrontabElement("1");
        crontab.monthElement = new CrontabElement("*");
        crontab.dayOfWeekElement = new CrontabElement("*");
        assertTrue(CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                .getCalendar(parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "6", "30", "20", "00", "00"),
                        crontab))),
                CalendarUtil.getCalendar("2010", "7", "1", "0", "25", "0")
                        .getTimeInMillis() == parser.getNextInvocationTime(
                        CalendarUtil.getCalendar("2010", "6", "30", "20", "00",
                                "00"), crontab));
        assertTrue(CalendarUtil.toYYYYMMDDHHMISS(CalendarUtil
                .getCalendar(parser.getNextInvocationTime(CalendarUtil
                        .getCalendar("2010", "7", "1", "1", "42", "34"),
                        crontab))),
                CalendarUtil.getCalendar("2010", "7", "1", "2", "25", "0")
                        .getTimeInMillis() == parser.getNextInvocationTime(
                        CalendarUtil.getCalendar("2010", "7", "1", "1", "42",
                                "34"), crontab));
    }

    @Test
    public void getFixedNextInvocationValue_A$int$List() throws Exception {
        int arg0 = 0;
        final List<Integer> arg1 = new ArrayList<Integer>();
        arg1.add(1);
        int actual = parser.getFixedNextInvocationValue(arg0, arg1);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void getFixedNextInvocationValue_A$int$int$RawCrontabLine()
            throws Exception {
        int arg0 = 0;
        int arg1 = 60;
        CrontabElement arg2 = new CrontabElement("*/3");
        int actual = parser.getFixedNextInvocationValue(arg0, arg1, arg2);
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void getIntervalInvocationCrontab_A$Crontab$RawCrontabLineArray()
            throws Exception {
        Crontab arg0 = new Crontab();
        String[] arg1 = new String[]{"interval:6sec", "initial:12sec",
                "java.lang.String*2", "server1"};
        Crontab actual = parser.getIntervalInvocationCrontab(arg0, arg1);
        assertEquals("java.lang.String", actual.commandClassName.toString());
        assertTrue(null == actual.dayElement);
        assertTrue(null == actual.dayFixedInvocations);
        assertTrue(null == actual.dayOfWeekElement);
        assertTrue(null == actual.dayOfWeekFixedInvocations);
        assertTrue(null == actual.hourElement);
        assertTrue(null == actual.hourFixedInvocations);
        assertTrue(12L == actual.initialIntervalSeconds);
        assertTrue(6L == actual.intervalSeconds);
        assertTrue(true == actual.isIntervalInvocation);
        assertTrue(null == actual.rawLine);
        assertTrue(null == actual.minuteElement);
        assertTrue(null == actual.minuteFixedInvocations);
        assertTrue(null == actual.monthElement);
        assertTrue(null == actual.monthFixedInvocations);
        assertTrue(2L == actual.multiplicity);
        assertTrue(1 == actual.namedServers.size());
    }

    @Test
    public void getLongValue_A$Crontab() throws Exception {
        String arg0 = "123sec";
        long actual = parser.getLongValue(arg0);
        long expected = 123L;
        assertEquals(expected, actual);
    }

    @Test
    public void getMultiplicity_A$Crontab() throws Exception {
        String arg0 = "com.m3.RunnableImpl*23";
        long actual = parser.getMultiplicityOfCommandWorkers(arg0);
        long expected = 23L;
        assertEquals(expected, actual);
    }

    @Test
    public void getCommandClassName_A$Crontab() throws Exception {
        String arg0 = "com.m3.RunnableImpl*23";
        CrontabCommandClassNameElement actual = parser.getCommandClassNameElement(arg0);
        CrontabCommandClassNameElement expected = new CrontabCommandClassNameElement(
                "com.m3.RunnableImpl");
        assertEquals(expected, actual);
    }

    @Test
    public void getNormalCrontab_A$Crontab$RawCrontabLineArray() throws Exception {
        Crontab arg0 = new Crontab();
        arg0.rawLine = new RawCrontabLine(
                "*/3 * * * * hoge.foo.SampleTask host1,host2");
        String[] arg1 = new String[]{"*/3", "*", "*", "*", "*",
                "hoge.foo.SampleTask*3", "host1,host2"};
        Crontab actual = parser.getBasicCrontab(arg0, arg1);
        assertEquals("hoge.foo.SampleTask", actual.commandClassName.toString());
        assertTrue(new CrontabElement("*").equals(actual.dayElement));
        assertTrue(null == actual.dayFixedInvocations);
        assertTrue(new CrontabElement("*").equals(actual.dayOfWeekElement));
        assertTrue(null == actual.dayOfWeekFixedInvocations);
        assertTrue(
                "" + actual.hourElement,
                new CrontabElement(
                        "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23")
                        .equals(actual.hourElement.toString()));
        assertTrue(24 == actual.hourFixedInvocations.size());
        assertTrue(0L == actual.initialIntervalSeconds);
        assertTrue(0L == actual.intervalSeconds);
        assertTrue(false == actual.isIntervalInvocation);
        assertTrue(new RawCrontabLine(
                "*/3 * * * * hoge.foo.SampleTask host1,host2")
                .equals(actual.rawLine));
        assertTrue(new CrontabElement("*/3").equals(actual.minuteElement.toString()));
        assertTrue(20 == actual.minuteFixedInvocations.size());
        assertTrue(new CrontabElement("*").equals(actual.monthElement));
        assertTrue(null == actual.monthFixedInvocations);
        assertTrue(3L == actual.multiplicity);
        assertTrue(2 == actual.namedServers.size());
    }

    @Test
    public void getCrontabDayOfWeekNumber_A$Calendar() throws Exception {
        Calendar cal = CalendarUtil.getCalendar("2010", "07", "01");
        int actual = parser.getCrontabDayOfWeekNumber(cal);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void isInvocationValueContainedInCrontabValue_A$int$int$RawCrontabLine()
            throws Exception {
        int invocationValue = 1;
        int maxValue = 3;
        CrontabElement crontabValue = new CrontabElement("1,2,3");
        boolean actual = parser.isInvocationValueContainedInCrontabValue(
                invocationValue, maxValue, crontabValue);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void type() throws Exception {
        assertNotNull(CrontabParser.class);
    }

    @Test
    public void instantiation() throws Exception {
        Class logImplClass = null;
        CrontabParser target = new CrontabParser(logImplClass);
        assertNotNull(target);
    }

}
