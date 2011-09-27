# taskun-spring-extension

taskun extension for connecting with Spring framework.
http://www.springsource.com/developer/spring

## How to install

### Maven

```xml
<repositories>
  <repository>
    <id>seratch.github.com releases</id>
    <url>http://seratch.github.com/mvn-repo/releases</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-spring-extension</artifactId>
    <version>[1.4,)</version>
  </dependency>
</dependencies>
```

## Snippet

### src/main/resources/applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
  "http://www.springframework.org/dtd/spring-beans.dtd">
<beans>
  <bean id="taskunConfig" class="com.github.seratch.taskun.scheduler.config.TaskunConfig">
  </bean>
  <bean id="taskun" class="com.github.seratch.taskun.scheduler.impl.TaskunImpl">
  </bean>
</beans>
```

### src/main/resources/crontab.txt

```
*/1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker
```

### src/main/webapp/WEB-INF/web.xml

```xml
<servlet>
  <servlet-name>springTaskunServlet</servlet-name>
  <servlet-class>com.github.seratch.taskun.servlet.impl.SpringContextTaskunServlet</servlet-class>
  <load-on-startup>3</load-on-startup>
</servlet>
```
