package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.servlet.impl.DefaultSchedulerServlet;
import junit.framework.TestCase;

public class AbstractSchedulerServletTest extends TestCase {

	protected AbstractSchedulerServlet target;
	protected SampleContainer container;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		target = new DefaultSchedulerServlet();
		container = new SampleContainer();
		target.containerAdaptor = container;
	}

	public void test_init_A$_start() throws Exception {
		container.config.enableInvokingScheduler = true;
		target.init();
	}

	public void test_init_A$_notstart() throws Exception {
		container.config.enableInvokingScheduler = false;
		target.init();
	}

	public void test_getContainerAdaptor_A$() throws Exception {
		DIContainerAdaptor actual = target.getContainerAdaptor();
		assertNotNull(actual);
	}

	public void test_setContainerAdaptor_A$ComponentContainer()
			throws Exception {
		DIContainerAdaptor arg0 = new SampleContainer();
		target.setContainerAdaptor(arg0);
	}

}
