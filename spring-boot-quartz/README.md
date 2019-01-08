# spring-boot-quartz

Spring Boot 配合 Quartz。

## 说明

### 添加依赖

如果SpringBoot版本是2.0.0以后的，则在spring-boot-starter中已经包含了quart的依赖，
则可以直接使用spring-boot-starter-quartz依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

如果是1.5.9则要使用以下添加依赖：

```xml
<dependency>
  <groupId>org.quartz-scheduler</groupId>
  <artifactId>quartz</artifactId>
  <version>2.3.0</version>
</dependency>
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-context-support</artifactId>
</dependency>
```

### 创建任务类 QuartzService

该类主要是继承了QuartzJobBean

```java
@Slf4j
public class QuartzService extends QuartzJobBean {

    /**
     * 执行定时任务
     *
     * @param jobExecutionContext
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("{} - quartz task.", System.currentTimeMillis());
    }
}
```


### 创建配置类 QuartzConfig

```java
@Configuration
public class QuartzConfig {

    /**
     * job任务
     * @return
     */
    @Bean
    public JobDetail teatQuartzDetail() {
        return JobBuilder.newJob(QuartzService.class)
                .withIdentity("quartzService")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger() {
        // 设置时间周期单位秒（10秒）
        // 永久执行
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
        // 触发
        return TriggerBuilder.newTrigger()
                .forJob(teatQuartzDetail())
                .withIdentity("quartzService")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
```

### 启动项目

查看输出日志

```
2019-01-08 19:00:57.997  [eduler_Worker-5] com.jeiker.quartz.service.QuartzService  : 1546945257997 - quartz task.
2019-01-08 19:01:07.995  [eduler_Worker-6] com.jeiker.quartz.service.QuartzService  : 1546945267995 - quartz task.
2019-01-08 19:01:17.997  [eduler_Worker-7] com.jeiker.quartz.service.QuartzService  : 1546945277997 - quartz task.
2019-01-08 19:01:27.994  [eduler_Worker-8] com.jeiker.quartz.service.QuartzService  : 1546945287994 - quartz task.
2019-01-08 19:01:37.992  [eduler_Worker-9] com.jeiker.quartz.service.QuartzService  : 1546945297992 - quartz task.
2019-01-08 19:01:47.994  [duler_Worker-10] com.jeiker.quartz.service.QuartzService  : 1546945307994 - quartz task.
```