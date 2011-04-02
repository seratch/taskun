package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.ServletInjector;
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
        target.injector = injector;
    }

    public void test_init_A$_start() throws Exception {
        injector.config.enableInvokingScheduler = true;
        target.init();
    }

    public void test_init_A$_notstart() throws Exception {
        injector.config.enableInvokingScheduler = false;
        target.init();
    }

    public void test_getInjector_A$() throws Exception {
        ServletInjector actual = target.getInjector();
        assertNotNull(actual);
    }

    public void test_setInjector_A$ServletInjector()
            throws Exception {
        ServletInjector arg0 = new SampleInjector();
        target.setInjector(arg0);
    }

}
