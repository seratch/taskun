package snippet;

import com.github.seratch.taskun.scheduler.Taskun;
import com.github.seratch.taskun.scheduler.config.TaskunConfig;
import com.github.seratch.taskun.scheduler.impl.TaskunImpl;

public class StartingSchedulerMain {

    public static void main(String[] args) throws Exception {
        Taskun taskun = new TaskunImpl();
        taskun.initialize(new TaskunConfig());
        taskun.replaceCrontabFile("snippet_crontab.txt");
        taskun.start();
        Thread.sleep(20000L);
    }

}
