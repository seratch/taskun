package com.github.seratch.taskun.servlet.snippet;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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
