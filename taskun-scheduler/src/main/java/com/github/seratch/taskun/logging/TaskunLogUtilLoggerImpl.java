package com.github.seratch.taskun.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskunLogUtilLoggerImpl implements TaskunLog {

    private String name;

    private Logger _log;

    static class ClassNameAndMethodName {
        public String className;
        public String methodName;

        ClassNameAndMethodName(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }
    }

    ClassNameAndMethodName getClassNameAndMethodName() {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        int stackDepth = 0;
        while (stackDepth < stack.length) {
            StackTraceElement frame = stack[stackDepth];
            if (frame.getClassName().equals("java.util.logging.Logger")) {
                break;
            }
            stackDepth++;
        }
        while (stackDepth < stack.length) {
            StackTraceElement frame = stack[stackDepth];
            if (!frame.getClassName().equals("java.util.logging.Logger")) {
                return new ClassNameAndMethodName(frame.getClassName(), frame.getMethodName());
            }
            stackDepth++;
        }
        return new ClassNameAndMethodName("", "");
    }

    public TaskunLogUtilLoggerImpl() {
        _log = Logger.getAnonymousLogger();
    }

    public TaskunLogUtilLoggerImpl(Class<?> clazz) {
        name = clazz.getCanonicalName();
        _log = Logger.getLogger(name);
    }

    public TaskunLogUtilLoggerImpl(String name) {
        this.name = name;
        _log = Logger.getLogger(name);
    }

    @Override
    public void debug(String message) {
        _log.fine(message);
    }

    @Override
    public void debug(String message, Throwable t) {
        ClassNameAndMethodName names = getClassNameAndMethodName();
        this._log.logp(Level.FINE, names.className, names.methodName, message, t);
    }

    @Override
    public void info(String message) {
        _log.info(message);
    }

    @Override
    public void info(String message, Throwable t) {
        ClassNameAndMethodName names = getClassNameAndMethodName();
        this._log.logp(Level.INFO, names.className, names.methodName, message, t);
    }

    @Override
    public void warn(String message) {
        ClassNameAndMethodName names = getClassNameAndMethodName();
        this._log.logp(Level.WARNING, names.className, names.methodName, message);
    }

    @Override
    public void warn(String message, Throwable t) {
        ClassNameAndMethodName names = getClassNameAndMethodName();
        this._log.logp(Level.WARNING, names.className, names.methodName, message, t);
    }

    @Override
    public void error(String message) {
        ClassNameAndMethodName names = getClassNameAndMethodName();
        this._log.logp(Level.SEVERE, names.className, names.methodName, message);
    }

    @Override
    public void error(String message, Throwable t) {
        ClassNameAndMethodName names = getClassNameAndMethodName();
        this._log.logp(Level.SEVERE, names.className, names.methodName, message, t);
    }

}
