package com.github.seratch.taskun.inject;

import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInjector implements Injector {


    protected ApplicationContext context = null;

    public SpringInjector() {
        if ( context == null ) {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");
        }
    }

    public SpringInjector(String applicationContextXML) {
        context = new ClassPathXmlApplicationContext(applicationContextXML);
    }

    @Override
    public SchedulerConfig getSchedulerConfig() {
        return context.getBean(SchedulerConfig.class);
    }

    @Override
    public <T> T inject(Class<?> clazz) {
        return (T) context.getBean(clazz);
    }


}
