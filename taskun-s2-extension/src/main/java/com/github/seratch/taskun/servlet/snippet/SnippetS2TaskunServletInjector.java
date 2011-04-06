package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.S2TaskunServletInjector;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class SnippetS2TaskunServletInjector extends S2TaskunServletInjector {

    public SnippetS2TaskunServletInjector() {
        SingletonS2ContainerFactory.init();
        SingletonS2ContainerFactory.setConfigPath("snippet_app.dicon");
        s2Container = SingletonS2ContainerFactory.getContainer();
    }

}
