package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.servlet.impl.DefaultSchedulerServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SnippetSchedulerServlet extends DefaultSchedulerServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void prepareToInit() {
        setInjector(new SnippetServletInjector());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setStatus(200);
        resp.getWriter().write("SnippetSchedulerServlet has invoked!");
    }

}
