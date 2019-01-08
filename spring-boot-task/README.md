# spring-boot-task

Spring Boot 使用 Task 来实现定时任务

# 说明


## 定时任务

使用三种参数方式，新建三个定时任务。

```java
@Slf4j
@Component
public class ScheduledService {

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        log.info("==>{} 使用cron。", System.currentTimeMillis());
    }

    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("==>{} 使用fixedRate。", System.currentTimeMillis());
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("==>{} 使用fixedDelay。", System.currentTimeMillis());
    }
}
```

## 定时任务开启


在主类`SpringBootTaskApplication`上使用`@EnableScheduling`注解开启对定时任务的支持，然后启动项目。

```java
@SpringBootApplication
@EnableScheduling // 开启Scheduling
public class SpringBootTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTaskApplication.class, args);
    }
}
```


发现输出都是在同一线程中执行：


```java
2019-01-08 17:24:58.207  INFO 40773 --- [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939498207 使用fixedRate。
2019-01-08 17:24:58.208  INFO 40773 --- [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939498208 使用fixedDelay。
2019-01-08 17:25:00.004  INFO 40773 --- [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939500004 使用cron。
2019-01-08 17:25:03.208  INFO 40773 --- [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939503208 使用fixedRate。
2019-01-08 17:25:03.209  INFO 40773 --- [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939503209 使用fixedDelay。
2019-01-08 17:25:05.002  INFO 40773 --- [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939505002 使用cron。
```
