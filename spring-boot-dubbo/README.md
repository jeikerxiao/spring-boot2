# spring-boot-dubbo

Dubbo 官方文档

http://dubbo.apache.org/zh-cn/docs/user/quick-start.html

Dubbo主要是一个分布式服务框架，致力于提供高性能和透明化的RPC远程服务调用方案，以及SOA服务治理方案。简单的说，Dubbo就是个服务框架，如果没有分布式的需求，其实是不需要用的。

ZooKeeper用来注册服务和进行负载均衡，哪一个服务由哪一个机器来提供必需让调用者知道，简单来说就是ip地址和服务名称的对应关系。ZooKeeper通过心跳机制可以检测挂掉的机器并将挂掉机器的ip和服务对应关系从列表中删除。



## 背景

随着互联网的发展，网站应用的规模不断扩大，常规的垂直应用架构已无法应对，分布式服务架构以及流动计算架构势在必行，亟需一个治理系统确保架构有条不紊的演进。


* 单一应用架构
* 垂直应用架构
* 分布式服务架构
* 流动计算架构

## 环境

推荐开发工具： IntelliJ IDEA

* JDK 1.8
* Spring Boot 2.0.3.RELEASE
* dubbo-spring-boot-starter 0.2.0

## 项目工程绍介

* dubbo-api  服务接口
* dubbo-provider 服务提供者
* dubbo-consumer 服务调用者


## 工程代码

### 父pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.jeiker.dubbo</groupId>
    <artifactId>spring-boot-dubbo</artifactId>
    <version>1.0.0</version>
    <packaging>pom</packaging>

    <name>Dubbo Spring Boot Samples</name>
    <description>Dubbo Spring Boot Samples</description>

    <modules>
        <module>dubbo-provider</module>
        <module>dubbo-consumer</module>
        <module>dubbo-api</module>
    </modules>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>

        <dubbo.version>0.2.0</dubbo.version>
        <dubbo.api.version>0.0.1</dubbo.api.version>
    </properties>

    <dependencies>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>

    </dependencies>
</project>
```

### dubbo-api

服务接口

HelloService.java

```java
public interface HelloService {

    String sayHello(String name);
}
```

### dubbo-provider

服务提供者

HelloServiceImpl.java

```java
@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + " (from Spring Boot Dubbo Provider)";
    }
}
```


application.properties

```
# Spring boot application
spring.application.name=dubbo-provider-demo
server.port=9090
# DemoService service version
demo.service.version=1.0.0
# Base packages to scan Dubbo Component: @com.alibaba.dubbo.config.annotation.Service
dubbo.scan.basePackages=com.jeiker.dubbo.provider.service
# Dubbo Config properties
## ApplicationConfig Bean
dubbo.application.id=dubbo-provider-demo
dubbo.application.name=dubbo-provider-demo
dubbo.application.qos.port=22222
dubbo.application.qos.enable=true
## ProtocolConfig Bean
dubbo.protocol.id=dubbo
dubbo.protocol.name=dubbo
dubbo.protocol.port=12345
dubbo.protocol.status=server
## RegistryConfig Bean
dubbo.registry.id=my-registry
dubbo.registry.address=N/A
# Enables Dubbo All Endpoints
management.endpoint.dubbo.enabled=true
management.endpoint.dubbo-shutdown.enabled=true
management.endpoint.dubbo-configs.enabled=true
management.endpoint.dubbo-services.enabled=true
management.endpoint.dubbo-references.enabled=true
management.endpoint.dubbo-properties.enabled=true
# Dubbo Health
## StatusChecker Name defaults (default : "memory", "load" )
management.health.dubbo.status.defaults=memory
## StatusChecker Name extras (default : empty )
management.health.dubbo.status.extras=load,threadpool
```

### dubbo-consumer

服务调用者

```java
@RestController
public class HelloController {

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return helloService.sayHello(name);
    }
}
```

application.properties

```
# Spring boot application
spring.application.name=dubbo-consumer-demo
server.port=8080
management.server.port=8081
# DemoService service version
demo.service.version=1.0.0
# Dubbo Config properties
## ApplicationConfig Bean
dubbo.application.id=dubbo-consumer-demo
dubbo.application.name=dubbo-consumer-demo
## Legacy QOS Config
dubbo.qos.port=22223
## ProtocolConfig Bean
dubbo.protocol.id=dubbo
dubbo.protocol.name=dubbo
dubbo.protocol.port=12345
# Dubbo Endpoint (default status is disable)
endpoints.dubbo.enabled=true
# Dubbo Health
## StatusChecker Name defaults (default : "memory", "load" )
management.health.dubbo.status.defaults=memory
# Enables Dubbo All Endpoints
management.endpoint.dubbo.enabled=true
management.endpoint.dubbo-shutdown.enabled=true
management.endpoint.dubbo-configs.enabled=true
management.endpoint.dubbo-services.enabled=true
management.endpoint.dubbo-references.enabled=true
management.endpoint.dubbo-properties.enabled=true
# Exposes all web endpoints
management.endpoints.web.exposure.include=*
```


## 测试

GET请求访问：

http://localhost:8080/sayHello?name=jeiker

可看到返回：

```
Hello, jeiker (from Spring Boot Dubbo Provider)
```

监控接口：

http://localhost:8081/actuator





