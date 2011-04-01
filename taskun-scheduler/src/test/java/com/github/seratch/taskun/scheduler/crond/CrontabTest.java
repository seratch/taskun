package com.github.seratch.taskun.scheduler.crond;

import junit.framework.TestCase;

public class CrontabTest extends TestCase {

	public void test_instantiation() throws Exception {
		Crontab crontab = new Crontab();
		assertNotNull(crontab);
	}

}
