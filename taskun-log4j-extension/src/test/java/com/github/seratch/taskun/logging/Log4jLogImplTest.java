package com.github.seratch.taskun.logging;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class Log4jLogImplTest {

    Class<?> clazz = Log4jLogImplTest.class;

    @Test
    public void type() throws Exception {
        assertNotNull(Log4jLogImpl.class);
    }

    @Test
    public void instantiation() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        assertNotNull(target);
    }

    @Test
    public void debug_A$String() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "debug_A$String";
        target.debug(message);
    }

    @Test
    public void info_A$String() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "info_A$String";
        target.info(message);
    }

    @Test
    public void info_A$String$Throwable() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "info_A$String$Throwable";
        Throwable t = new RuntimeException("aaa");
        target.info(message, t);
    }

    @Test
    public void warn_A$String() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "warn_A$String";
        target.warn(message);
    }

    @Test
    public void warn_A$String$Throwable() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "warn_A$String$Throwable";
        Throwable t = new RuntimeException("aaa");
        target.warn(message, t);
    }

    @Test
    public void error_A$String() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "error_A$String";
        target.error(message);
    }

    @Test
    public void error_A$String$Throwable() throws Exception {
        Log4jLogImpl target = new Log4jLogImpl(clazz);
        String message = "error_A$String$Throwable";
        Throwable t = new RuntimeException("aaa");
        target.error(message, t);
    }

}
