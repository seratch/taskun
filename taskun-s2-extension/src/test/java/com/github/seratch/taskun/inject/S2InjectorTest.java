package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.inject.S2Injector.*;
import static org.junit.Assert.*;
import org.junit.Test;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class S2InjectorTest {

	@Test
	public void type() throws Exception {
		assertNotNull(S2Injector.class);
	}

	@Test
	public void instantiation() throws Exception {
		S2Injector target = new S2Injector();
		assertNotNull(target);
	}

	@Test
	public void getSchedulerConfig_A$() throws Exception {
		S2Injector target = new S2Injector();
		SchedulerConfig actual = target.getSchedulerConfig();
        assertNotNull(actual);
	}

	@Test
	public void inject_A$Class() throws Exception {
		S2Injector target = new S2Injector();
		Class<?> clazz = SchedulerConfig.class;
		Object actual = target.inject(clazz);
        assertNotNull(actual);
	}

}
