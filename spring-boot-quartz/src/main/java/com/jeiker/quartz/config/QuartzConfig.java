package com.jeiker.quartz.config;

import com.jeiker.quartz.service.QuartzService;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: Quartz配置类
 * User: jeikerxiao
 * Date: 2019/1/8 6:46 PM
 */
@Configuration
public class QuartzConfig {

    /**
     * job任务
     *
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