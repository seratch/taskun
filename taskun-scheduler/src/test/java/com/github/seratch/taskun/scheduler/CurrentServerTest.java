package com.github.seratch.taskun.scheduler;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import junit.framework.TestCase;

import java.net.InetAddress;

public class CurrentServerTest extends TestCase {

    public void test_isWorkingOnNamedServerHost_A$SchedulerConfig$String_completelyMatched()
            throws Exception {
        String arg0 = "test2";
        SchedulerConfig config = new SchedulerConfig();
        config.putNamedServer("test2", InetAddress.getLocalHost()
                .getCanonicalHostName());
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, arg0);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    public void test_isWorkingOnNamedServerHost_A$SchedulerConfig$String_prefixMatched()
            throws Exception {
        String arg0 = "test2";
        SchedulerConfig config = new SchedulerConfig();
        String hostname = InetAddress.getLocalHost().getCanonicalHostName();
        config.putNamedServer("test2", hostname.replaceAll("\\..+", ".*"));
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, arg0);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    public void test_isWorkingOnNamedServerHost_A$SchedulerConfig$String_false()
            throws Exception {
        String arg0 = "test3";
        SchedulerConfig config = new SchedulerConfig();
        boolean actual = CurrentServer.isWorkingOnNamedServerHost(config, arg0);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    public void test_getWorkingServerName_A$SchedulerConfig$String()
            throws Exception {
        SchedulerConfig config = new SchedulerConfig();
        String actual = CurrentServer.getServerName(config);
        assertNotNull(actual);
    }

    public void test_getHostname_A$() throws Exception {
        String actual = CurrentServer.getHostname();
        assertNotNull(actual);
    }

}
