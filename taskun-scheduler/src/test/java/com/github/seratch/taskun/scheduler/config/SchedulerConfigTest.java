package com.github.seratch.taskun.scheduler.config;

import junit.framework.TestCase;

public class SchedulerConfigTest extends TestCase {

	public void test_putNamedServer_A$String$String() throws Exception {
		// given
		String name = "hoge";
		String hostname = "hosthost";
		// when
		SchedulerConfig target = new SchedulerConfig();
		assertFalse(target.namedServers.containsKey(name));
		target.putNamedServer(name, hostname);
		// then
		assertTrue(target.namedServers.containsKey(name));
	}

	public void test_removeNamedServer_A$String() throws Exception {
		// given
		String name = "hoge";
		String hostname = "hosthost";
		SchedulerConfig target = new SchedulerConfig();
		assertFalse(target.namedServers.containsKey(name));
		target.putNamedServer(name, hostname);
		assertTrue(target.namedServers.containsKey(name));
		// when
		target.removeNamedServer(name);
		// then
		assertFalse(target.namedServers.containsKey(name));
	}

	public void test_getNamedServerHostname_A$String() throws Exception {
		// given
		String name = "hoge";
		String hostname = "hosthost";
		SchedulerConfig target = new SchedulerConfig();
		assertFalse(target.namedServers.containsKey(name));
		target.putNamedServer(name, hostname);
		assertTrue(target.namedServers.containsKey(name));
		// when
		String actual = target.getNamedServerHostname(name);
		// then
		assertEquals(hostname, actual);
	}

}
