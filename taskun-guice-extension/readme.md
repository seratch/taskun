# taskun-guice-extension

taskun extension for connecting with Google Guice.
http://code.google.com/p/google-guice/

## How to install

### Maven

```xml
<dependencies>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-guice-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
</dependencies>
```

## Snippet #1: Servlet 2.4 example

### src/main/java/.../SnippetGuiceTaskunServlet.java

```java
public class SnippetGuiceTaskunServlet extends AbstractGuiceSchedulerServlet {
  @Override
  public Module[] getPreparedModules() {
    return new Module[] {new AbstractModule() {
      @Override
      protected void configure() {
        Taskun taskun = TaskunFactory.getInstance();
        taskun.replaceCrontabFile("snippet_crontab.txt");
        bind(Taskun.class).toInstance(taskun);
      }
    }};
  }
}
```

### src/main/resources/snippet_crontab.txt

```
*/1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker
```

### src/main/webapp/WEB-INF/web.xml

```xml
<servlet>
  <servlet-name>guiceTaskunServlet</servlet-name>
  <servlet-class>com.github.seratch.taskun.servlet.snippet.SnippetGuiceTaskunServlet</servlet-class>
  <load-on-startup>3</load-on-startup>
</servlet>
```

## Snippet #2: Servlet 3 example

### src/main/java/.../SnippetGuiceTaskunServlet.java

NOTE: You need a pattern mapping on a servlet, a simply `@WebServlet(loadOnStartup = 3)` won't initialize it.

```java
@WebServlet(urlPatterns = "/taskun", loadOnStartup = 3)
public class SnippetGuiceTaskunServlet extends AbstractGuiceSchedulerServlet {
  @Override
  public Module[] getPreparedModules() {
    return new Module[] {new AbstractModule() {
      @Override
      protected void configure() {
        Taskun taskun = TaskunFactory.getInstance();
        taskun.replaceCrontabFile("snippet_crontab.txt");
        bind(Taskun.class).toInstance(taskun);
      }
    }};
  }
}
```

### src/main/resources/snippet_crontab.txt

```
*/1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker
```
