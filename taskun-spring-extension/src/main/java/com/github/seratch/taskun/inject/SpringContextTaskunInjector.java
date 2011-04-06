package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextTaskunInjector implements TaskunInjector {


    protected ApplicationContext context = null;

    public SpringContextTaskunInjector() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
    }

    public SpringContextTaskunInjector(String applicationContextXML) {
        context = new ClassPathXmlApplicationContext(applicationContextXML);
    }

    @Override
    public TaskunConfig getTaskunConfig() {
        return context.getBean(TaskunConfig.class);
    }


    @Override
    public <T> T inject(Class<?> clazz) {
        return (T) context.getBean(clazz);
    }


}
