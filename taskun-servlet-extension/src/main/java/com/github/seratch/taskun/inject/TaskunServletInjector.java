package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.Taskun;

public interface TaskunServletInjector extends TaskunInjector {

    Taskun getTaskun();

}
