package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract Http servlet class that invokes taskun-taskun.<br>
 * The web applications which uses taskun-taskun needs to have an
 * implementation class.
 *
 * @author Kazuhiro Sera
 */
public abstract class AbstractSchedulerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected Logger log = Logger.getLogger(AbstractSchedulerServlet.class.getCanonicalName());

    /**
     * Prepare to init<br>
     * Need to set taskunInjector adaptor
     */
    protected abstract void prepareToInit();

    protected Taskun taskun;

    protected TaskunConfig taskunConfig;

    protected TaskunServletInjector taskunInjector;

    public AbstractSchedulerServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        prepareToInit();
        if (taskunInjector == null) {
            throw new IllegalStateException(
                    "taskunInjector required before init!");
        }
        taskunConfig = taskunInjector.getTaskunConfig();
        StringBuilder sb = new StringBuilder();
        if (taskunConfig.enableInvokingScheduler) {
            taskun = taskunInjector.getTaskun();
            taskun.initialize(taskunInjector);
            taskun.start();
            sb.append("Taskun-taskun has started!\n");
            if (taskunConfig.namedServers != null) {
                sb.append("[NamedServers:");
                for (String key : taskunConfig.namedServers.keySet()) {
                    sb.append(key);
                    sb.append("->");
                    sb.append(taskunConfig.namedServers.get(key));
                    sb.append(",");
                }
                sb.append("]");
            }
        } else {
            sb.append("Taskun-taskun is not running... ");
        }
        log.logp(Level.INFO, this.getClass().getCanonicalName(), "init",
                sb.toString());
    }

    @Override
    public void destroy() {
        try {
            if (taskunConfig != null
                    && taskunConfig.enableInvokingScheduler) {
                this.taskun.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.destroy();
        }
    }

    public TaskunServletInjector getTaskunInjector() {
        return taskunInjector;
    }

    public void setTaskunInjector(TaskunServletInjector taskunInjector) {
        this.taskunInjector = taskunInjector;
    }

}
