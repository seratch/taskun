# taskun-log4j-extension

taskun extension for Log4J.
http://logging.apache.org/log4j/

## Install

### Download jar file

    /download/taskun-log4j-extension-1.*.jar

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
        <version>[1,)</version>
      </dependency>
      ...
    </dependencies>

## Snippet

It is required to set Log4JLogImpl.class to SchedulerConfig.

    @Test
    public void using() throws Exception {
        Scheduler scheduler = new TaskunScheduler();
        SchedulerConfig config = new SchedulerConfig();
        config.setLogImplClass(Log4jLogImpl.class);
        scheduler.initialize(config);
        scheduler.start();
        Thread.sleep(2000L);
    }


