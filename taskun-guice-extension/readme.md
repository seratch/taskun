# taskun-guice-extension

taskun extension for connecting with Google Guice.
http://code.google.com/p/google-guice/

## Install

### Download jar file

    /download/taskun-guice-extension-1.4.*.jar

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
        <artifactId>taskun-guice-extension</artifactId>
        <version>[1.4,)</version>
      </dependency>
      ...
    </dependencies>

## Snippet

### src/main/java/.../SnippetGuiceTaskunServlet.java

    public class SnippetGuiceTaskunServlet extends AbstractGuiceTaskunServlet {
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

### src/main/resources/snippet_crontab.txt

    */1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
    interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker

### src/main/webapp/WEB-INF/web.xml

    <servlet>
      <servlet-name>guiceTaskunServlet</servlet-name>
      <servlet-class>com.github.seratch.taskun.servlet.snippet.SnippetGuiceTaskunServlet</servlet-class>
      <load-on-startup>3</load-on-startup>
    </servlet>

