# spring-boot-task

Spring Boot 使用 Task 来实现定时任务

# 说明

## 单线程方式

### 定时任务

使用三种参数方式，新建三个定时任务。

在下面的定时任务中，在方法上使用`@Scheduled`注解来设置任务的执行时间，并且使用三种属性配置方式：

* cron：通过表达式来配置任务执行时间
* fixedRate：定义一个按一定频率执行的定时任务
* fixedDelay：定义一个按一定频率执行的定时任务，与上面不同的是，改属性可以配合initialDelay， 定义该任务延迟执行时间。



```java
@Slf4j
@Component
public class ScheduledService {

    /**
     * cron：通过表达式来配置任务执行时间
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        log.info("==>{} 使用cron。", System.currentTimeMillis());
    }

    /**
     * fixedRate：定义一个按一定频率执行的定时任务
     */
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("==>{} 使用fixedRate。", System.currentTimeMillis());
    }

    /**
     * fixedDelay：定义一个按一定频率执行的定时任务，
     * 与上面不同的是，改属性可以配合 initialDelay， 定义该任务延迟执行时间。
     */
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("==>{} 使用fixedDelay。", System.currentTimeMillis());
    }
}
```

### 定时任务开启


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

### 日志输出

发现输出都是在同一线程中执行：


```java
2019-01-08 17:24:58.207  [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939498207 使用fixedRate。
2019-01-08 17:24:58.208  [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939498208 使用fixedDelay。
2019-01-08 17:25:00.004  [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939500004 使用cron。
2019-01-08 17:25:03.208  [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939503208 使用fixedRate。
2019-01-08 17:25:03.209  [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939503209 使用fixedDelay。
2019-01-08 17:25:05.002  [   scheduling-1] c.jeiker.task.service.ScheduledService   : ==>1546939505002 使用cron。
```

## 多线程方式


### 多线程配置

@Configuration：表明该类是一个配置类 
@EnableAsync：开启异步事件的支持

```java
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
    * 此处成员变量应该使用@Value从配置中读取
    */
    private int corePoolSize = 10;
    private int maxPoolSize = 200;
    private int queueCapacity = 10;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
```

然后在定时任务的类或者方法上添加`@Async` 。最后重启项目，每一个任务都是在不同的线程中.

### 异步注解

```java
@Slf4j
@Component
public class ScheduledService {

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled() {
        log.info("==>{} 使用cron。", System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("==>{} 使用fixedRate。", System.currentTimeMillis());
    }

    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("==>{} 使用fixedDelay。", System.currentTimeMillis());
    }
}
```

### 日志输出

发现输出是在不同线程中执行：

```java
2019-01-08 18:20:50.006  [ taskExecutor-6] c.jeiker.task.service.ScheduledService   : ==>1546942850006 使用cron。
2019-01-08 18:20:53.714  [ taskExecutor-7] c.jeiker.task.service.ScheduledService   : ==>1546942853714 使用fixedRate。
2019-01-08 18:20:53.716  [ taskExecutor-8] c.jeiker.task.service.ScheduledService   : ==>1546942853716 使用fixedDelay。
2019-01-08 18:20:55.003  [ taskExecutor-9] c.jeiker.task.service.ScheduledService   : ==>1546942855003 使用cron。
2019-01-08 18:20:58.711  [taskExecutor-10] c.jeiker.task.service.ScheduledService   : ==>1546942858711 使用fixedRate。
2019-01-08 18:20:58.717  [ taskExecutor-1] c.jeiker.task.service.ScheduledService   : ==>1546942858717 使用fixedDelay。
2019-01-08 18:21:00.005  [ taskExecutor-2] c.jeiker.task.service.ScheduledService   : ==>1546942860005 使用cron。
2019-01-08 18:21:03.711  [ taskExecutor-3] c.jeiker.task.service.ScheduledService   : ==>1546942863711 使用fixedRate。
2019-01-08 18:21:03.719  [ taskExecutor-4] c.jeiker.task.service.ScheduledService   : ==>1546942863719 使用fixedDelay。
2019-01-08 18:21:05.003  [ taskExecutor-5] c.jeiker.task.service.ScheduledService   : ==>1546942865003 使用cron。
2019-01-08 18:21:08.714  [ taskExecutor-6] c.jeiker.task.service.ScheduledService   : ==>1546942868714 使用fixedRate。
2019-01-08 18:21:08.721  [ taskExecutor-7] c.jeiker.task.service.ScheduledService   : ==>1546942868721 使用fixedDelay。
```