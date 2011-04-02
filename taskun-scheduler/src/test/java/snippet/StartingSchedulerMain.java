package snippet;

import com.github.seratch.taskun.scheduler.Scheduler;
import com.github.seratch.taskun.scheduler.config.SchedulerConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunScheduler;

public class StartingSchedulerMain {

    public static void main(String[] args) throws Exception {
        Scheduler scheduler = new TaskunScheduler();
        scheduler.initialize(new SchedulerConfig());
        scheduler.replaceCrontabFile("snippet_crontab.txt");
        scheduler.start();
        Thread.sleep(20000L);
    }

}
