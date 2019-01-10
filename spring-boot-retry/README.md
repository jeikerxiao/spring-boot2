# spring-boot-retry

Spring Boot 整合 Spring Retry。

# 说明

## @Retryable注解

被注解的方法发生异常时会重试 

* value：指定发生的异常进行重试 
* include：和value一样，默认空，当exclude也为空时，所有异常都重试 
* exclude：指定异常不重试，默认空，当include也为空时，所有异常都重试 
* maxAttemps：重试次数，默认3 
* backoff：重试补偿机制，默认没有

## @Backoff注解

* delay:指定延迟后重试 
* multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒

## @Recover 

当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调。

# 代码

## pom

spring-retry需要依赖AOP

```xml
<!-- Retry -->
<dependency>
	<groupId>org.springframework.retry</groupId>
	<artifactId>spring-retry</artifactId>
</dependency>
<!-- Retry 需要依赖aop -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

## 服务层


```java
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

```

## 控制层

```java
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/call")
    public Map<String, String> call() {
        try {
            userService.call();
        } catch (Exception e) {
            log.error("call userService error: {}", e);
        }
        return Collections.singletonMap("message", "hello");
    }
}
```

## 主类上

增加`@EnableRetry`的开关

```java
@SpringBootApplication
@EnableRetry
public class SpringBootRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRetryApplication.class, args);
	}

}


```


# 测试

GET http://localhost:8080/user/call


日志输出:

```
c.j.retry.service.impl.UserServiceImpl   : call(): 15:18:03.462 do something...
c.j.retry.service.impl.UserServiceImpl   : call(): 15:18:08.467 do something...
c.j.retry.service.impl.UserServiceImpl   : call(): 15:18:13.471 do something...
c.j.retry.service.impl.UserServiceImpl   : recover(): RPC调用异常
```