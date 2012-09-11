package com.github.seratch.taskun.scheduler.crond;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CrontabTest {

    @Test
    public void instantiation() throws Exception {
        Crontab crontab = new Crontab();
        assertNotNull(crontab);
    }

    @Test
    public void type() throws Exception {
        assertNotNull(Crontab.class);
    }

    @Test
    public void isServerToInvoke_A$String() throws Exception {
        Crontab target = new Crontab();
        String serverName = null;
        boolean actual = target.isServerToInvoke(serverName);
        boolean expected = true;
        assertEquals(expected, actual);
    }

}
