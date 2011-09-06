package com.github.seratch.taskun.scheduler.config;

import com.github.seratch.taskun.logging.TaskunLog;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TaskunConfigTest {

    @Test
    public void setNamedServers_A$Map() throws Exception {
        // given
        Map<String, String> namedServers = new HashMap<String, String>();
        namedServers.put("hoge", "hogehoge");
        // when
        TaskunConfig target = new TaskunConfig();
        assertFalse(target.namedServers.containsKey("hoge"));
        target.setNamedServers(namedServers);
        // then
        assertTrue(target.namedServers.containsKey("hoge"));
    }

    @Test
    public void putNamedServer_A$String$String() throws Exception {
        // given
        String name = "hoge";
        String hostname = "hosthost";
        // when
        TaskunConfig target = new TaskunConfig();
        assertFalse(target.namedServers.containsKey(name));
        target.putNamedServer(name, hostname);
        // then
        assertTrue(target.namedServers.containsKey(name));
    }

    @Test
    public void removeNamedServer_A$String() throws Exception {
        // given
        String name = "hoge";
        String hostname = "hosthost";
        TaskunConfig target = new TaskunConfig();
        assertFalse(target.namedServers.containsKey(name));
        target.putNamedServer(name, hostname);
        assertTrue(target.namedServers.containsKey(name));
        // when
        target.removeNamedServer(name);
        // then
        assertFalse(target.namedServers.containsKey(name));
    }

    @Test
    public void getNamedServerHostname_A$String() throws Exception {
        // given
        String name = "hoge";
        String hostname = "hosthost";
        TaskunConfig target = new TaskunConfig();
        assertFalse(target.namedServers.containsKey(name));
        target.putNamedServer(name, hostname);
        assertTrue(target.namedServers.containsKey(name));
        // when
        String actual = target.getNamedServerHostname(name);
        // then
        assertEquals(hostname, actual);
    }

    @Test
    public void type() throws Exception {
        assertNotNull(TaskunConfig.class);
    }

    @Test
    public void instantiation() throws Exception {
        TaskunConfig target = new TaskunConfig();
        assertNotNull(target);
    }

    @Test
    public void setLogImplClass_A$Class() throws Exception {
        TaskunConfig target = new TaskunConfig();
        Class<TaskunLog> logImplClass = null;
        target.setLogImplClass(logImplClass);
    }

    @Test
    public void getLogImplClass_A$() throws Exception {
        TaskunConfig target = new TaskunConfig();
        Object actual = target.getLogImplClass();
        assertNotNull(actual);
    }

}
