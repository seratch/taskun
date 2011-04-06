package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.servlet.impl.DefaultSchedulerServlet;
import junit.framework.TestCase;

public class AbstractSchedulerServletTest extends TestCase {

    protected AbstractSchedulerServlet target;
    protected SampleInjector injector;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        target = new DefaultSchedulerServlet();
        injector = new SampleInjector();
        target.taskunInjector = injector;
    }

    public void test_init_A$_start() throws Exception {
        injector.getTaskunConfig().enableInvokingScheduler = true;
        target.init();
    }

    public void test_init_A$_notstart() throws Exception {
        injector.getTaskunConfig().enableInvokingScheduler = false;
        target.init();
    }

    public void test_getTaskunInjector_A$() throws Exception {
        TaskunServletInjector actual = target.getTaskunInjector();
        assertNotNull(actual);
    }

    public void test_setTaskunInjector_A$TaskunServletInjector()
            throws Exception {
        TaskunServletInjector arg0 = new SampleInjector();
        target.setTaskunInjector(arg0);
    }

}
