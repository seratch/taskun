package com.github.seratch.taskun.servlet.snippet;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SpringEchoWorker implements Runnable {

    @Resource
    SpringBean bean;

    @Override
    public void run() {
        bean.saySomething();
    }

}
