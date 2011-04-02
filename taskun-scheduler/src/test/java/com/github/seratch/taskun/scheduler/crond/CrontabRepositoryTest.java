package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.logging.UtilLoggerImpl;
import junit.framework.TestCase;

import java.util.ArrayList;

public class CrontabRepositoryTest extends TestCase {

    CrontabParser parser = new CrontabParser(UtilLoggerImpl.class);
    CrontabRepository repos = new CrontabRepository(new ArrayList<Crontab>());

    public void test_remove_A$RawCrontabLine() throws Exception {
        String arg0 = "0 1 * * * hoge.foo.Var server1";
        repos.remove(parser.parseLine(new RawCrontabLine(arg0)));
    }

    public void test_edit_A$RawCrontabLine$RawCrontabLine() throws Exception {
        String arg0 = "0 1 * * * hoge.foo.Var server1";
        String arg1 = "interval:5sec  initial:16sec hoge.foo.Var server1";
        repos.replace(parser.parseLine(new RawCrontabLine(arg0)), parser.parseLine(new RawCrontabLine(arg1)));
    }

    public void test_isAlreadyRegistered_A$String() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine("0 1 * * * hoge.foo.Var server1");
        assertFalse(repos.isAlreadyRegistered(parser.parseLine(arg0)));
        repos.add(parser.parseLine(arg0));
        assertTrue(repos.isAlreadyRegistered(parser.parseLine(arg0)));
    }

    public void test_find_A$String() throws Exception {
        Crontab arg0 = parser.parseLine(new RawCrontabLine(
                "0 1 * * * hoge.foo.Var server1"));
        Object actual1 = repos.find(arg0);
        Crontab expected1 = null;
        assertEquals(expected1, actual1);
        repos.add(arg0);
        Crontab expected2 = new Crontab();
        expected2.rawLine = arg0.rawLine;
        Crontab actual2 = repos.find(arg0);
        assertEquals(expected2.rawLine, actual2.rawLine);
    }

}
