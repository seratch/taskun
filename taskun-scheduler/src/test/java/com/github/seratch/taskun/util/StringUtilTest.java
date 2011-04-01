package com.github.seratch.taskun.util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

	public void test_isEmpty_A$String() throws Exception {
		assertTrue(StringUtil.isEmpty(null));
		assertTrue(StringUtil.isEmpty(""));
		assertFalse(StringUtil.isEmpty("abc"));
	}

}
