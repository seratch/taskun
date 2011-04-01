package com.github.seratch.taskun.scheduler.crond;

import com.github.seratch.taskun.common.DIContainerAdaptor;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import junit.framework.TestCase;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

public class CronDaemonTest extends TestCase {

	private CronDaemon cronDaemon;
	private DIContainerAdaptor containerAdaptor;
	private ScheduledExecutorService executorService;
	private SchedulerConfig schedulerConfig;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		cronDaemon = new CronDaemon();
	}

	protected void initialize(Mockery context) {
		containerAdaptor = context.mock(DIContainerAdaptor.class);
		executorService = context.mock(ScheduledExecutorService.class);
		schedulerConfig = new SchedulerConfig();
		context.checking(new Expectations() {
			{
			}
		});
	}

	public void test_run_A$() throws Exception {
		Mockery context = new Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
		initialize(context);
		cronDaemon.initialize(containerAdaptor, executorService);
		context.checking(new Expectations() {
			{
				exactly(2).of(containerAdaptor).getSchedulerConfig();
				will(returnValue(schedulerConfig));
			}
		});
		cronDaemon.run();
	}

	public void test_initialize_A$ComponentContainerAdaptor$ScheduledExecutorService()
			throws Exception {
		Mockery context = new Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
		initialize(context);
		cronDaemon.initialize(containerAdaptor, executorService);
	}

	public void test_initialize_A$ComponentContainerAdaptor$ScheduledExecutorService$String()
			throws Exception {
		Mockery context = new Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
		initialize(context);
		cronDaemon.initialize(containerAdaptor, executorService, "filename");
	}

	public void test_addCrontab_A$String() throws Exception {
		try {
			cronDaemon.addCrontabLine(new RawCrontabLine("aaa"));
			fail("Expected : IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
		cronDaemon.addCrontabLine(new RawCrontabLine(
				"0 1 * * * hoge.foo.Var server1"));
	}

	public void test_loggingAtEachInvocation_A$String() throws Exception {
		Mockery context = new Mockery() {
			{
				setImposteriser(ClassImposteriser.INSTANCE);
			}
		};
		initialize(context);
		cronDaemon.initialize(containerAdaptor, executorService);
		context.checking(new Expectations() {
			{
				one(containerAdaptor).getSchedulerConfig();
				will(returnValue(schedulerConfig));
			}
		});
		String arg0 = "aaa";
		cronDaemon.loggingAtEachInvocation(arg0);
	}

	public void test_getSchedulingStringList_A$() throws Exception {
		List<RawCrontabLine> actual = cronDaemon.getCurrentRawCrontabLines();
		assertNotNull(actual);
	}

}
