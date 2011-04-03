# taskun-spring-extension

taskun extension for connecting with Spring framework.
http://www.springsource.com/developer/spring

## Install

### Download jar file

    /download/taskun-spring-extension-1.*.jar

### Maven

    <repositories>
      ...
      <repository>
        <id>taskun-releases</id>
        <url>https://github.com/seratch/taskun/raw/master/mvn-repo/releases</url>
      </repository>
      <repository>
        <id>taskun-snapshots</id>
        <url>https://github.com/seratch/taskun/raw/master/mvn-repo/snapshots</url>
      </repository>
      ...
    </repositories>

    <dependencies>
      ...
      <dependency>
        <groupId>com.github.seratch.taskun</groupId>
        <artifactId>taskun-spring-extension</artifactId>
        <version>[1,)</version>
      </dependency>
      ...
    </dependencies>

## Snippet

### src/main/resources/applicationContext.xml

    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
      "http://www.springframework.org/dtd/spring-beans.dtd">
    <beans>
      <bean id="schedulerConfig" class="com.github.seratch.taskun.scheduler.config.SchedulerConfig">
      </bean>
      <bean id="scheduler" class="com.github.seratch.taskun.scheduler.impl.TaskunScheduler">
      </bean>
    </beans>

### src/main/resources/crontab.txt

    */1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
    interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker

### src/main/webapp/WEB-INF/web.xml

    <servlet>
      <servlet-name>springSchedulerServlet</servlet-name>
      <servlet-class>com.github.seratch.taskun.servlet.impl.SpringSchedulerServlet</servlet-class>
      <load-on-startup>3</load-on-startup>
    </servlet>

