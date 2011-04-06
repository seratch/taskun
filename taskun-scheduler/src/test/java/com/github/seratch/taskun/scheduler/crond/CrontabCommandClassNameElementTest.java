package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.scheduler.crond.CrontabCommandClassNameElement.*;
import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CrontabCommandClassNameElementTest {

    @Test
    public void type() throws Exception {
        assertNotNull(CrontabCommandClassNameElement.class);
    }

    @Test
    public void instantiation() throws Exception {
        String commandClassName = null;
        CrontabCommandClassNameElement target = new CrontabCommandClassNameElement(commandClassName);
        assertNotNull(target);
    }

    @Test
    public void toString_A$() throws Exception {
        String commandClassName = null;
        CrontabCommandClassNameElement target = new CrontabCommandClassNameElement(commandClassName);
        String actual = target.toString();
        assertNotNull(actual);
    }

    @Test
    public void equals_A$Object() throws Exception {
        String commandClassName = "dsfsdf";
        CrontabCommandClassNameElement target = new CrontabCommandClassNameElement(commandClassName);
        Object obj = null;
        boolean actual = target.equals(obj);
        boolean expected = false;
        assertEquals(expected, actual);
    }

}
