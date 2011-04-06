package com.github.seratch.taskun.logging;

import org.apache.commons.logging.LogFactory;

public class TaskunLogLog4jImpl implements TaskunLog {

    private org.apache.commons.logging.Log _log;

    public TaskunLogLog4jImpl(Class<?> clazz) {
        this(clazz.getCanonicalName());
    }

    public TaskunLogLog4jImpl(String name) {
        _log = LogFactory.getLog(name);
    }

    @Override
    public void debug(String message) {
        _log.debug(message);
    }

    @Override
    public void debug(String message, Throwable t) {
        _log.debug(message, t);
    }

    @Override
    public void info(String message) {
        _log.info(message);
    }

    @Override
    public void info(String message, Throwable t) {
        _log.info(message, t);
    }

    @Override
    public void warn(String message) {
        _log.warn(message);
    }

    @Override
    public void warn(String message, Throwable t) {
        _log.warn(message, t);
    }

    @Override
    public void error(String message) {
        _log.error(message);
    }

    @Override
    public void error(String message, Throwable t) {
        _log.error(message, t);
    }
}
