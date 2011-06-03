package com.github.seratch.taskun.servlet.snippet;

import org.springframework.stereotype.Component;

@Component
public class SpringBean {

    public void saySomething() {
        System.out.println("Injected Spring component!");
    }

}
