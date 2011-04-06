package com.github.seratch.taskun.logging;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TaskunLogLog4jImplTest {

    Class<?> clazz = TaskunLogLog4jImplTest.class;

    @Test
    public void type() throws Exception {
        assertNotNull(TaskunLogLog4jImpl.class);
    }

    @Test
    public void instantiation() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        assertNotNull(target);
    }

    @Test
    public void debug_A$String() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "debug_A$String";
        target.debug(message);
    }

    @Test
    public void info_A$String() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "info_A$String";
        target.info(message);
    }

    @Test
    public void info_A$String$Throwable() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "info_A$String$Throwable";
        Throwable t = new RuntimeException("aaa");
        target.info(message, t);
    }

    @Test
    public void warn_A$String() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "warn_A$String";
        target.warn(message);
    }

    @Test
    public void warn_A$String$Throwable() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "warn_A$String$Throwable";
        Throwable t = new RuntimeException("aaa");
        target.warn(message, t);
    }

    @Test
    public void error_A$String() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "error_A$String";
        target.error(message);
    }

    @Test
    public void error_A$String$Throwable() throws Exception {
        TaskunLogLog4jImpl target = new TaskunLogLog4jImpl(clazz);
        String message = "error_A$String$Throwable";
        Throwable t = new RuntimeException("aaa");
        target.error(message, t);
    }

}
