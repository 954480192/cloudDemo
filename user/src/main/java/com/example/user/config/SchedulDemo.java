package com.example.user.config;

import com.example.user.service.UserService;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
public class SchedulDemo {
    @Autowired
    UserService userService;

    /*
    Seconds : 可出现", - * /"四个字符，有效范围为0-59的整数
    Minutes : 可出现", - * /"四个字符，有效范围为0-59的整数
    Hours : 可出现", - * /"四个字符，有效范围为0-23的整数
    DayofMonth : 可出现", - * / ? L W C"八个字符，有效范围为0-31的整数
    Month : 可出现", - * /"四个字符，有效范围为1-12的整数或JAN-DEc
    DayofWeek : 可出现", - * / ? L C #"四个字符，有效范围为1-7的整数或SUN-SAT两个范围。1表示星期天，2表示星期一， 依次类推
    Year : 可出现", - * /"四个字符，有效范围为1970-2099年

    fixedRate:上一个调用开始后再次调用的延时（不用等待上一次调用完成）
    initialDelay:第一次执行延迟时间(秒）
    fixedDelay:会等到方法执行完成后延迟
     */
    @Scheduled(cron = "0/10 * * ? * *")
    public void test(){
        System.out.println("定时器触发时间："+ LocalDateTime.now().getSecond());
    }

    public void newSchedul() throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        JobDetail job = JobBuilder.newJob(DemoJob.class).withIdentity("job1","group1").build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(job,cronTrigger);
        scheduler.start();
    }

    public static void main(String[] args) {
        try {
            new SchedulDemo().newSchedul();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

//@DisallowConcurrentExecution
class DemoJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("----执行方法---");
    }
}
