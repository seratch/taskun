# taskun - A simple cron daemon thread on the JVM

## What's this

"taskun" is an implementation of UNIX "crond" on the JVM, and it also has some extended notations.

## How to install

### Maven

```xml
<dependencies>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-scheduler</artifactId>
    <version>[1.5,)</version>
  </dependency>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-servlet-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
  
  <!-- OPTIONAL EXTENSIONS -->
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-log4j-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-guice-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-spring-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
  <dependency>
    <groupId>com.github.seratch.taskun</groupId>
    <artifactId>taskun-s2-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
</dependencies>
```

## Snippet1: Running taskun 

### src/main/java/snippet/EchoWorker.java

```java
package snippet;
public class EchoWorker implements Runnable {
  public void run() {
    System.out.println("Hello, World!");
  }
}
```

### src/main/resources/crontab.txt

```
*/1 * * * * snippet.EchoWorker
```

### src/main/java/snippet/SampleMain.java

```java
package snippet;
public static void main(String[] args) throws Exception {
  Taskun taskun = TaskunFactory.getInstance();
  taskun.initialize(new TaskunConfig());
  taskun.start();
  Thread.sleep(20000L);
}
```

## Snippet2: Running snippet servlet

```sh
git clone git@github.com:seratch/taskun.git
cd taskun/taskun-servlet-extension
mvn jetty:run

# And you will see "Hello, World!" endlessly in the console.

[INFO] Started Jetty Server
[INFO] Starting scanner at interval of 3 seconds.
2011/04/02 0:00:53 com.github.seratch.taskun.taskun.crond.CronDaemon initialize
INFO: ----- Taskun scheduler initialized -----
2011/04/02 0:00:53 com.github.seratch.taskun.taskun.crond.CronDaemon initialize
INFO: Working at seratch-imac(seratch-imac)
2011/04/02 0:00:53 com.github.seratch.taskun.taskun.crond.CronDaemon initialize
INFO: Interval invocation : 3sec,com.github.seratch.taskun.servlet.snippet.EchoWorker,1
2011/04/02 0:00:53 com.github.seratch.taskun.taskun.crond.CronDaemon initialize
INFO: Crontab invocation : */1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
2011/04/02 0:00:53 com.github.seratch.taskun.taskun.crond.CronDaemon initialize
INFO: ----------------------------------------
2011/04/02 0:00:53 com.github.seratch.taskun.servlet.snippet.SnippetTaskunServlet init
INFO: Taskun scheduler has started!
[NamedServers:]
Hello, World! (Thread:20,Sat Apr 02 00:00:58 JST 2011)
Hello, World! (Thread:21,Sat Apr 02 00:01:00 JST 2011)
Hello, World! (Thread:20,Sat Apr 02 00:01:00 JST 2011)
...
```

## Snippet3: Running taskun in your webapp

### src/main/webapp/WEB-INF/web.xml

```xml
<servlet>
  <servlet-name>taskunServlet</servlet-name>
  <servlet-class>com.github.seratch.taskun.servlet.impl.SimpleTaskunServlet</servlet-class>
  <load-on-startup>3</load-on-startup>
</servlet>
```

### src/main/resources/crontab.txt

```
*/1 * * * * com.github.seratch.taskun.servlet.snippet.EchoWorker*3
interval:3sec initial:5sec com.github.seratch.taskun.servlet.snippet.EchoWorker
```
### src/main/resources/taskun.properties

```properties
enableInvokingTaskun=true
enableLoggingForEachCrondInvocation=true
namedServer1=.*server1
namedServer2=server2*.example.com
namedServer3=
```

### Deploy and run

## Snippet4: Injecting taskun, config and worker instances

NOTICE: taskun-guice-extension, taskun-spring-extension or taskun-s2-extension is required.

+ [taskun-guice-extension](https://github.com/seratch/taskun/blob/master/taskun-guice-extension/readme.md)
+ [taskun-spring-extension](https://github.com/seratch/taskun/blob/master/taskun-spring-extension/readme.md)
+ [taskun-s2-extension](https://github.com/seratch/taskun/blob/master/taskun-s2-extension/readme.md)

## Snippet5: Using extended notations

### Invoking seconds intervals

"initial" is waiting time for the first invoking since taskun taskun started.

```
interval:3sec initial:5sec snippet.EchoWorker
```

### Invoking several threads at once.

Following will invoke 3 threads to do same command(=EchoWorker) at once:

```
*/1 * * * * snippet.EchoWorker*3
```

## Snippet6: Using log4j for taskun logging

NOTICE: taskun-log4j-extension is required.

+ [taskun-log4j-extension](https://github.com/seratch/taskun/blob/master/taskun-log4j-extension/readme.md)

It is reuirqed to set LogImplClass to config.

```java
package snippet;
 
import com.github.seratch.taskun.logging.TaskunLogLog4jImpl;
 
public static void main(String[] args) throws Exception {
  Taskun taskun = TaskunFactory.getInstance();
  TaskunConfig config = new TaskunConfig();
  config.setLogImplClass(TaskunLogLog4jImpl.class); 
  taskun.initialize(config);
  taskun.start();
  Thread.sleep(20000L);
}
```
