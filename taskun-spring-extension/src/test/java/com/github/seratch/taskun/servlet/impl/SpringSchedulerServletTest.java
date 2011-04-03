package com.github.seratch.taskun.servlet.impl;

import com.github.seratch.taskun.servlet.impl.SpringSchedulerServlet.*;
import static org.junit.Assert.*;
import org.junit.Test;

import com.github.seratch.taskun.inject.SpringServletInjector;
import com.github.seratch.taskun.servlet.AbstractSchedulerServlet;

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
