package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.inject.S2ServletInjector.*;
import static org.junit.Assert.*;
import org.junit.Test;

import com.github.seratch.taskun.scheduler.Scheduler;

public class S2ServletInjectorTest {

	@Test
	public void type() throws Exception {
		assertNotNull(S2ServletInjector.class);
	}

	@Test
	public void instantiation() throws Exception {
		S2ServletInjector target = new S2ServletInjector();
		assertNotNull(target);
	}

	@Test
	public void getScheduler_A$() throws Exception {
		S2ServletInjector target = new S2ServletInjector();
		Scheduler actual = target.getScheduler();
        assertNotNull(actual);
	}

}
