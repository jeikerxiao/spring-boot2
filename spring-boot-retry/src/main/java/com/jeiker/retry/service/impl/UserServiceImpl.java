package com.jeiker.retry.service.impl;

import com.jeiker.retry.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/1/10 2:58 PM
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    @Retryable(value = {RemoteAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000L, multiplier = 1)
    )
    public void call() throws Exception {
        log.info("call(): {} do something...", LocalTime.now());
        throw new RemoteAccessException("RPC调用异常");
    }

    @Recover
    public void recover(RemoteAccessException e) {
        log.info("recover(): {}", e.getMessage());
    }
}
