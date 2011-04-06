package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;

public class S2TaskunServletInjector extends S2TaskunInjector implements TaskunServletInjector {

    @Override
    public Taskun getTaskun() {
        return (Taskun) s2Container.getComponent(Taskun.class);
    }

}
