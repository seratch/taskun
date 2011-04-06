package com.github.seratch.taskun.servlet;

import com.github.seratch.taskun.inject.GuiceTaskunServletInjector;
import com.google.inject.Module;

public abstract class AbstractGuiceSchedulerServlet extends AbstractSchedulerServlet {


    public abstract Module[] getPreparedModules();

    @Override
    protected void prepareToInit() {
<<<<<<< HEAD
        setTaskunInjector(new GuiceTaskunServletInjector(getPreparedModule()));
=======
        setInjector(new GuiceServletInjector(getPreparedModules()));
>>>>>>> 14d61b2d295382cf742762a76387be4ac184d977
    }


}
