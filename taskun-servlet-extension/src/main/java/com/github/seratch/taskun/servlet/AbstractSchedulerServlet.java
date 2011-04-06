package com.github.seratch.taskun.servlet;

<<<<<<< HEAD
import com.github.seratch.taskun.inject.TaskunServletInjector;
import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
=======
import com.github.seratch.taskun.inject.ServletInjector;
import com.github.seratch.taskun.logging.Log;
import com.github.seratch.taskun.logging.UtilLoggerImpl;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
>>>>>>> 14d61b2d295382cf742762a76387be4ac184d977

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Abstract Http servlet class that invokes taskun-taskun.<br>
 * The web applications which uses taskun-taskun needs to have an
 * implementation class.
 *
 * @author Kazuhiro Sera
 */
public abstract class AbstractSchedulerServlet extends HttpServlet {

<<<<<<< HEAD
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
=======
	private static final long serialVersionUID = 1L;

	private Log log = getLog(AbstractSchedulerServlet.class.getCanonicalName());

	Log getLog() {
		return getLog(AbstractSchedulerServlet.class.getCanonicalName());
	}

	Log getLog(String name) {
		try {
			return schedulerConfig.getLogImplClass().getConstructor(String.class).newInstance(name);
		} catch (Throwable t) {
			return new UtilLoggerImpl(name);
		}
	}

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
		log.info(sb.toString());
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
>>>>>>> 14d61b2d295382cf742762a76387be4ac184d977

}
