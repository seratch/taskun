# taskun-log4j-extension

taskun extension for Log4J.
http://logging.apache.org/log4j/

## Install

### Download jar file

    /download/taskun-log4j-extension-1.4.*.jar

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
        <artifactId>taskun-log4j-extension</artifactId>
        <version>[1.4,)</version>
      </dependency>
      ...
    </dependencies>

## Snippet

It is required to set Log4JLogImpl.class to TaskunConfig.

    @Test
    public void using() throws Exception {
        Taskun taskun = TaskunFactory.getInstance();
        TaskunConfig config = new TaskunConfig();
        config.setLogImplClass(TaskunLogLog4jImpl.class);
        taskun.initialize(config);
        taskun.start();
        Thread.sleep(2000L);
    }


