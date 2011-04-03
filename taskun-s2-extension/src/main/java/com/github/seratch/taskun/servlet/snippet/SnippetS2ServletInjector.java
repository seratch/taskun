package com.github.seratch.taskun.servlet.snippet;

import com.github.seratch.taskun.inject.S2ServletInjector;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class SnippetS2ServletInjector extends S2ServletInjector {

    public SnippetS2ServletInjector() {
        SingletonS2ContainerFactory.init();
        SingletonS2ContainerFactory.setConfigPath("snippet_app.dicon");
        s2Container = SingletonS2ContainerFactory.getContainer();
    }

}
