package com.github.seratch.taskun.servlet.impl;

import static org.junit.Assert.*;
import org.junit.Test;

public class SpringContextTaskunServletTest {

	@Test
	public void type() throws Exception {
		assertNotNull(SpringContextTaskunServlet.class);
	}

	@Test
	public void instantiation() throws Exception {
		SpringContextTaskunServlet target = new SpringContextTaskunServlet();
		assertNotNull(target);
	}

	@Test
	public void prepareToInit_A$() throws Exception {
		SpringContextTaskunServlet target = new SpringContextTaskunServlet();
		target.prepareToInit();
	}

}
