package com.github.seratch.taskun.common;

import junit.framework.TestCase;

public class DIContainerAdaptorTest extends TestCase {

	public void test_available() throws Exception {
		DIContainerAdaptor container = new SampleComponentContainer();
		assertNotNull(container);
	}

}
