package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.logging.TaskunLog;
import com.github.seratch.taskun.logging.TaskunLogUtilLoggerImpl;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Abstract Http servlet class that invokes taskun-taskun.<br>
 * The web applications which uses taskun-taskun needs to have an
 * implementation class.
 *
 * @author Kazuhiro Sera
 */
public abstract class AbstractTaskunServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private TaskunLog log;

    TaskunLog getLog() {
        return getLog(this.getClass().getCanonicalName());
    }

    TaskunLog getLog(String name) {
        try {
            return config.getLogImplClass().getConstructor(String.class).newInstance(name);
        } catch (Throwable t) {
            return new TaskunLogUtilLoggerImpl(name);
        }
    }

    /**
     * Prepare to init<br>
     * Need to set injector adaptor
     */
    protected abstract void prepareToInit();

    protected Taskun taskun;

    protected TaskunConfig config;

    protected TaskunServletInjector injector;

    public AbstractTaskunServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        prepareToInit();
        if (injector == null) {
            throw new IllegalStateException(
                    "injector required before init!");
        }
        config = injector.getTaskunConfig();
        log = getLog();
        StringBuilder sb = new StringBuilder();
        if (config.enableInvokingScheduler) {
            taskun = injector.getTaskun();
            taskun.initialize(injector);
            taskun.start();
            sb.append("Taskun scheduler has started!\n");
            if (config.namedServers != null) {
                sb.append("[NamedServers:");
                for (String key : config.namedServers.keySet()) {
                    sb.append(key);
                    sb.append("->");
                    sb.append(config.namedServers.get(key));
                    sb.append(",");
                }
                sb.append("]");
            }
        } else {
            sb.append("Taskun scheduler is not running... ");
        }
        log.info(sb.toString());
    }

    @Override
    public void destroy() {
        try {
            if (config != null
                    && config.enableInvokingScheduler) {
                this.taskun.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.destroy();
        }
    }

    public TaskunServletInjector getTaskunInjector() {
        return injector;
    }

    public void setTaskunInjector(TaskunServletInjector injector) {
        this.injector = injector;
    }


}
