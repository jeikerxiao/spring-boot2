package com.jeiker.aspects.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Description: 日志切面
 * User: jeikerxiao
 * Date: 2019/1/14 11:43 AM
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 切点
     * 该方法代表controller层的所有方法
     */
    @Pointcut("execution(public * com.jeiker.aspects.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）。
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        log.info("@Before - 方法之前执行");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * 后置通知：在某连接点正常完成后执行的通知，通常在一个匹配的方法返回的时候执行。
     * 可以在后置通知中绑定返回值
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("@AfterReturning - 方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知：在方法抛出异常退出时执行的通知。
     *
     * @param jp
     */
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        log.info("@AfterThrowing - 方法异常时执行.....");
    }

    /**
     * 后置最终通知：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
     *
     * @param jp
     */
    @After("webLog()")
    public void after(JoinPoint jp) {
        log.info("@After - 方法最后执行.....");
    }

    /**
     * 环绕通知：包围一个连接点的通知。
     * 环绕通知可以在方法调用前后完成自定义的行为。
     * 它也会选择是否继续执行连接点或直接返回它自己的返回值或抛出异常来结束执行。
     *
     * @param pjp
     * @return
     */
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        log.info("@Around - 方法环绕start.....");
        try {
            Object o = pjp.proceed();
            log.info("@Around - 方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            log.info("@Around - 方法环绕exception:{}", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}