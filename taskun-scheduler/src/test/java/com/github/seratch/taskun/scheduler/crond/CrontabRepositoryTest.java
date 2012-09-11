package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.logging.TaskunLogUtilLoggerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CrontabRepositoryTest {

    CrontabParser parser = new CrontabParser(TaskunLogUtilLoggerImpl.class);
    CrontabRepository repos = new CrontabRepository(new ArrayList<Crontab>());

    @Test
    public void remove_A$Crontab() throws Exception {
        String arg0 = "0 1 * * * hoge.foo.Var server1";
        repos.remove(parser.parseLine(new RawCrontabLine(arg0)));
    }

    @Test
    public void replace_A$Crontab$Crontab() throws Exception {
        String arg0 = "0 1 * * * hoge.foo.Var server1";
        String arg1 = "interval:5sec  initial:16sec hoge.foo.Var server1";
        repos.replace(parser.parseLine(new RawCrontabLine(arg0)), parser.parseLine(new RawCrontabLine(arg1)));
    }

    @Test
    public void isAlreadyRegistered_A$Crontab() throws Exception {
        RawCrontabLine arg0 = new RawCrontabLine("0 1 * * * hoge.foo.Var server1");
        assertFalse(repos.isAlreadyRegistered(parser.parseLine(arg0)));
        repos.add(parser.parseLine(arg0));
        assertTrue(repos.isAlreadyRegistered(parser.parseLine(arg0)));
    }

    @Test
    public void find_A$Crontab() throws Exception {
        Crontab arg0 = parser.parseLine(new RawCrontabLine("0 1 * * * hoge.foo.Var server1"));
        Object actual1 = repos.find(arg0);
        Crontab expected1 = null;
        assertEquals(expected1, actual1);
        repos.add(arg0);
        Crontab expected2 = new Crontab();
        expected2.rawLine = arg0.rawLine;
        Crontab actual2 = repos.find(arg0);
        assertEquals(expected2.rawLine, actual2.rawLine);
    }

    @Test
    public void type() throws Exception {
        assertNotNull(CrontabRepository.class);
    }

    @Test
    public void instantiation() throws Exception {
        List<Crontab> crontabLines = null;
        CrontabRepository target = new CrontabRepository(crontabLines);
        assertNotNull(target);
    }

    @Test
    public void add_A$Crontab_null() throws Exception {
        List<Crontab> crontabLines = new ArrayList<Crontab>();
        CrontabRepository target = new CrontabRepository(crontabLines);
        Crontab crontab = null;
        target.add(crontab);
    }

    @Test
    public void add_A$Crontab_rawLineIsNull() throws Exception {
        List<Crontab> crontabLines = new ArrayList<Crontab>();
        CrontabRepository target = new CrontabRepository(crontabLines);
        Crontab crontab = new Crontab();
        target.add(crontab);
    }

}
