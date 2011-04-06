package com.github.seratch.taskun.inject;

import junit.framework.TestCase;

public class TaskunInjectorTest extends TestCase {

    public void test_available() throws Exception {
        TaskunInjector taskunInjector = new SampleTaskunInjector();
        assertNotNull(taskunInjector);
    }

}
