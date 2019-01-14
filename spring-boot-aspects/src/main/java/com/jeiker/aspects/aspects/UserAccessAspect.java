package com.jeiker.aspects.aspects;

import com.jeiker.aspects.annotation.UserAccess;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/14 2:35 PM
 */
@Aspect
@Component
@Slf4j
public class UserAccessAspect {

    @Pointcut(value = "@annotation(com.jeiker.aspects.annotation.UserAccess)")
    public void access() {

    }

    @Before("access()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        log.info("second before");
    }

    @Around("@annotation(userAccess)")
    public Object around(ProceedingJoinPoint pjp, UserAccess userAccess) {
        //获取注解里的值
        log.info("second around:" + userAccess.desc());
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
