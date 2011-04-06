package com.github.seratch.taskun.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.github.seratch.taskun.logging.TaskunLogUtilLoggerImpl.*;
import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TaskunLogUtilLoggerImplTest {

    @Test
    public void type() throws Exception {
        assertNotNull(TaskunLogUtilLoggerImpl.class);
    }

    @Test
    public void instantiation() throws Exception {
        TaskunLogUtilLoggerImpl target = new TaskunLogUtilLoggerImpl();
        assertNotNull(target);
    }

    TaskunLog log = new TaskunLogUtilLoggerImpl();

    @Test
    public void getClassNameAndMethodName_A$() throws Exception {
        TaskunLogUtilLoggerImpl.ClassNameAndMethodName actual
                = new TaskunLogUtilLoggerImpl().getClassNameAndMethodName();
        assertEquals("", actual.className);
        assertEquals("", actual.methodName);
    }

    @Test
    public void debug_A$String() throws Exception {
        String message = null;
        log.debug(message);
    }

    @Test
    public void debug_A$String$Throwable() throws Exception {
        String message = null;
        Throwable t = null;
        log.debug(message, t);
    }

    @Test
    public void info_A$String() throws Exception {
        String message = null;
        log.info(message);
    }

    @Test
    public void info_A$String$Throwable() throws Exception {
        String message = null;
        Throwable t = null;
        log.info(message, t);
    }

    @Test
    public void warn_A$String() throws Exception {
        String message = null;
        log.warn(message);
    }

    @Test
    public void warn_A$String$Throwable() throws Exception {
        String message = null;
        Throwable t = null;
        log.warn(message, t);
    }

    @Test
    public void error_A$String() throws Exception {
        String message = null;
        log.error(message);
    }

    @Test
    public void error_A$String$Throwable() throws Exception {
        String message = null;
        Throwable t = null;
        log.error(message, t);
    }

}
