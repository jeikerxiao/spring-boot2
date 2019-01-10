# spring-boot-hsqldb

Spring Boot 整合 hsqldb (内存数据库)。

# 说明

使用HSQLDB和H2代码基本一样是一样的，只是在添加依赖的时候不一样，修改pom.xml文件，去掉或者注释掉h2的依赖，引入hsqldb的依赖：

```xml
<dependency>
	<groupId>org.hsqldb</groupId>
	<artifactId>hsqldb</artifactId>
	<scope>runtime</scope>
</dependency>
```

启动日志时：

H2对应信息：

```java
org.hibernate.dialect.Dialect  : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect；
```

HSQL对应信息：

```java
org.hibernate.dialect.Dialect  : HHH000400: Using dialect: org.hibernate.dialect.HSQLDialect；
```

# 测试

## 添加用户

POST http://localhost:8080/user

```javascript
{
	"age":23,
	"name":"Tom"
}
```

GET http://localhost:8080/user

```javascript
{
    "result": [
        {
            "id": 1,
            "age": 11,
            "name": "xiao"
        },
        {
            "id": 2,
            "age": 20,
            "name": "jeiker"
        },
        {
            "id": 3,
            "age": 23,
            "name": "Tom"
        }
    ]
}
```

GET http://localhost:8080/user/name/xiao

```javascript
{
    "result": {
        "id": 2,
        "age": 20,
        "name": "jeiker"
    }
}
```

GET http://localhost:8080/user/id/2

```javascript
{
    "result": {
        "id": 1,
        "age": 11,
        "name": "xiao"
    }
}
```

DELETE http://localhost:8080/user/3

然后再查看

GET http://localhost:8080/user

```javascript
{
    "result": [
        {
            "id": 1,
            "age": 11,
            "name": "xiao"
        },
        {
            "id": 2,
            "age": 20,
            "name": "jeiker"
        }
    ]
}
```

