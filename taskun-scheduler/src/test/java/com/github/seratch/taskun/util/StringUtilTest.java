package com.github.seratch.taskun.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void isEmpty_A$String() throws Exception {
        assertTrue(StringUtil.isEmpty(null));
        assertTrue(StringUtil.isEmpty(""));
        assertFalse(StringUtil.isEmpty("abc"));
    }

    @Test
    public void type() throws Exception {
        assertNotNull(StringUtil.class);
    }

    @Test
    public void instantiation() throws Exception {
        StringUtil target = new StringUtil();
        assertNotNull(target);
    }

}
