package com.github.seratch.taskun.logging;

import org.apache.commons.logging.LogFactory;

public class Log4jLogImpl implements Log {

    private org.apache.commons.logging.Log _log;

    public Log4jLogImpl(Class<?> clazz) {
        this(clazz.getCanonicalName());
    }

    public Log4jLogImpl(String name) {
        _log = LogFactory.getLog(name);
    }


    @Override
    public void debug(String message) {
        _log.debug(message);
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
