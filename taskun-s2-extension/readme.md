# taskun-s2-extension

taskun extension for connecting with S2Container.
http://s2container.seasar.org/2.4/ja/DIContainer.html

## Install

### Download jar file

    /download/taskun-s2-extension-1.4.*.jar

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
        <artifactId>taskun-s2-extension</artifactId>
        <version>[1.4,)</version>
      </dependency>
      ...
    </dependencies>

## Snippet

### src/main/resources/app.dicon

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE components PUBLIC "-//SEASAR//DTD S2Container 2.4//EN"
        "http://www.seasar.org/dtd/components24.dtd">
    <components>
      <component name="taskun"
        class="com.github.seratch.taskun.scheduler.impl.TaskunImpl">
      </component>
      <component name="taskunConfig"
        class="com.github.seratch.taskun.scheduler.config.TaskunConfig">
      </component>
    </components>

### src/main/resources/crontab.txt

    */1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
    interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker

### src/main/webapp/WEB-INF/web.xml

    <servlet>
      <servlet-name>s2SchedulerServlet</servlet-name>
      <servlet-class>com.github.seratch.taskun.servlet.impl.S2TaskunServlet</servlet-class>
      <load-on-startup>3</load-on-startup>
    </servlet>

