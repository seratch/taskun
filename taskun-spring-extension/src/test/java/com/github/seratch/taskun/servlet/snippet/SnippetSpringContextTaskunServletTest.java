package com.github.seratch.taskun.servlet.snippet;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SnippetSpringContextTaskunServletTest {

    @Test
    public void type() throws Exception {
        assertNotNull(SnippetSpringContextTaskunServlet.class);
    }

    @Test
    public void instantiation() throws Exception {
        SnippetSpringContextTaskunServlet target = new SnippetSpringContextTaskunServlet();
        assertNotNull(target);
    }

    @Test
    public void prepareToInit_A$() throws Exception {
        SnippetSpringContextTaskunServlet target = new SnippetSpringContextTaskunServlet();
        target.prepareToInit();
    }

}
