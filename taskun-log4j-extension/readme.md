# taskun-log4j-extension

taskun extension for Log4J.
http://logging.apache.org/log4j/

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
    <artifactId>taskun-log4j-extension</artifactId>
    <version>[1.5,)</version>
  </dependency>
</dependencies>
```

## Snippet

It is required to set Log4JLogImpl.class to TaskunConfig.

```java
@Test
public void using() throws Exception {
    Taskun taskun = TaskunFactory.getInstance();
    TaskunConfig config = new TaskunConfig();
    config.setLogImplClass(TaskunLogLog4jImpl.class);
    taskun.initialize(config);
    taskun.start();
    Thread.sleep(2000L);
}
```

