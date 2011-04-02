package com.github.seratch.taskun.inject;

import junit.framework.TestCase;

public class InjectorTest extends TestCase {

    public void test_available() throws Exception {
        Injector injector = new SampleInjector();
        assertNotNull(injector);
    }

}
