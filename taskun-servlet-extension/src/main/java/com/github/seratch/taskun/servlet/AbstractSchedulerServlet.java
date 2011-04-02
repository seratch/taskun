package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.ServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract Http servlet class that invokes taskun-scheduler.<br>
 * The web applications which uses taskun-scheduler needs to have an
 * implementation class.
 *
 * @author Kazuhiro Sera
 */
public abstract class AbstractSchedulerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected Logger log = Logger.getLogger(AbstractSchedulerServlet.class.getCanonicalName());

    /**
     * Prepare to init<br>
     * Need to set injector adaptor
     */
    protected abstract void prepareToInit();

    protected Scheduler scheduler;

    protected SchedulerConfig schedulerConfig;

    protected ServletInjector injector;

    public AbstractSchedulerServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        prepareToInit();
        if (injector == null) {
            throw new IllegalStateException(
                    "injector required before init!");
        }
        schedulerConfig = injector.getSchedulerConfig();
        StringBuilder sb = new StringBuilder();
        if (schedulerConfig.enableInvokingScheduler) {
            scheduler = injector.getScheduler();
            scheduler.initialize(injector);
            scheduler.start();
            sb.append("Taskun-scheduler has started!\n");
            if (schedulerConfig.namedServers != null) {
                sb.append("[NamedServers:");
                for (String key : schedulerConfig.namedServers.keySet()) {
                    sb.append(key);
                    sb.append("->");
                    sb.append(schedulerConfig.namedServers.get(key));
                    sb.append(",");
                }
                sb.append("]");
            }
        } else {
            sb.append("Taskun-scheduler is not running... ");
        }
        log.logp(Level.INFO, this.getClass().getCanonicalName(), "init",
                sb.toString());
    }

    @Override
    public void destroy() {
        try {
            if (schedulerConfig != null
                    && schedulerConfig.enableInvokingScheduler) {
                this.scheduler.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.destroy();
        }
    }

    public ServletInjector getInjector() {
        return injector;
    }

    public void setInjector(ServletInjector injector) {
        this.injector = injector;
    }

}
