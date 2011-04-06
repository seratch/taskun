package com.github.seratch.taskun.servlet.impl;

import static org.junit.Assert.*;
import org.junit.Test;

public class SpringSchedulerServletTest {

	@Test
	public void type() throws Exception {
		assertNotNull(SpringSchedulerServlet.class);
	}

	@Test
	public void instantiation() throws Exception {
		SpringSchedulerServlet target = new SpringSchedulerServlet();
		assertNotNull(target);
	}

	@Test
	public void prepareToInit_A$() throws Exception {
		SpringSchedulerServlet target = new SpringSchedulerServlet();
		target.prepareToInit();
	}

}
