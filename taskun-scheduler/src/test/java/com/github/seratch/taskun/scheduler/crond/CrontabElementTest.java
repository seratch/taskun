package com.github.seratch.taskun.scheduler.crond;

import org.junit.Test;

import static org.junit.Assert.*;

public class CrontabElementTest {

    @Test
    public void isNotWildcard_A$_false() throws Exception {
        CrontabElement element = new CrontabElement("*");
        boolean actual = element.isNotWildcard();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void isNotWildcard_A$String_true() throws Exception {
        String[] arg = new String[]{"1", "*/3", "1-59/3", "1,2,3"};
        for (int i = 0; i < arg.length; i++) {
            assertTrue(arg[i], new CrontabElement(arg[i]).isNotWildcard());
        }
    }

    @Test
    public void isFixedValue_A$String_true() throws Exception {
        assertTrue(new CrontabElement("5").isFixedValue());
        assertTrue(new CrontabElement("54").isFixedValue());
        assertTrue(new CrontabElement("999").isFixedValue());
    }

    @Test
    public void isFixedValue_A$String_false() throws Exception {
        assertFalse(new CrontabElement("5*").isFixedValue());
        assertFalse(new CrontabElement("12/3").isFixedValue());
        assertFalse(new CrontabElement("*/2").isFixedValue());
        assertFalse(new CrontabElement("2,3,4*").isFixedValue());
        assertFalse(new CrontabElement("").isFixedValue());
    }

    @Test
    public void type() throws Exception {
        assertNotNull(CrontabElement.class);
    }

    @Test
    public void instantiation() throws Exception {
        String element = null;
        CrontabElement target = new CrontabElement(element);
        assertNotNull(target);
    }

}
