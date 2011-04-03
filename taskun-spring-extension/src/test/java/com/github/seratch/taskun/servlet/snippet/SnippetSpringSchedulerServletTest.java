package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.servlet.snippet.SnippetSpringSchedulerServlet.*;
import static org.junit.Assert.*;
import org.junit.Test;

import com.github.seratch.taskun.inject.SpringServletInjector;
import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.servlet.impl.SpringSchedulerServlet;

public class SnippetSpringSchedulerServletTest {

	@Test
	public void type() throws Exception {
		assertNotNull(SnippetSpringSchedulerServlet.class);
	}

	@Test
	public void instantiation() throws Exception {
		SnippetSpringSchedulerServlet target = new SnippetSpringSchedulerServlet();
		assertNotNull(target);
	}

	@Test
	public void prepareToInit_A$() throws Exception {
		SnippetSpringSchedulerServlet target = new SnippetSpringSchedulerServlet();
		target.prepareToInit();
	}

}
