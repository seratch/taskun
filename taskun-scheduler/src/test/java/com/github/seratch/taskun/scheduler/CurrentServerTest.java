package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.junit.Test;

import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CurrentServerTest {

    @Test
    public void isWorkingOnNamedServerHost_A$SchedulerConfig$String_completelyMatched()
            throws Exception {
        String arg0 = "test2";
        TaskunConfig config = new TaskunConfig();
        config.putNamedServer("test2", InetAddress.getLocalHost()
                .getCanonicalHostName());
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, arg0);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void isWorkingOnNamedServerHost_A$SchedulerConfig$String_prefixMatched()
            throws Exception {
        String arg0 = "test2";
        TaskunConfig config = new TaskunConfig();
        String hostname = InetAddress.getLocalHost().getCanonicalHostName();
        config.putNamedServer("test2", hostname.replaceAll("\\..+", ".*"));
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, arg0);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void isWorkingOnNamedServerHost_A$SchedulerConfig$String_false()
            throws Exception {
        String arg0 = "test3";
        TaskunConfig config = new TaskunConfig();
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, arg0);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void getWorkingServerName_A$SchedulerConfig$String()
            throws Exception {
        TaskunConfig config = new TaskunConfig();
        String actual = CurrentServer.getServerName(config);
        assertNotNull(actual);
    }

    @Test
    public void getHostname_A$() throws Exception {
        String actual = CurrentServer.getHostname();
        assertNotNull(actual);
    }

    @Test
    public void type() throws Exception {
        assertNotNull(CurrentServer.class);
    }

    @Test
    public void instantiation() throws Exception {
        CurrentServer target = new CurrentServer();
        assertNotNull(target);
    }

    @Test
    public void isWorkingOnNamedServerHost_A$TaskunConfig$String() throws Exception {
        TaskunConfig config = new TaskunConfig();
        String name = "aaa";
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, name);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void getServerName_A$TaskunConfig() throws Exception {
        TaskunConfig config = new TaskunConfig();
        String actual = CurrentServer.getServerName(config);
        assertNotNull(actual);
    }

}
