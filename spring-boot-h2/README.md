# spring-boot-h2

Spring Boot 整合 H2（内存数据库）。

# 说明

||H2|Derby|HSQLDB|MySQL|PostgreSQL|
|:--|:--|:--|:--|:--|:--|
|Pure Java(纯Java编写)|Yes|Yes|Yes|No|No
|Memory Mode（支持内存模式）|Yes|Yes|Yes|No|No
|Encrypted Database（支持数据库加密）|Yes|Yes|Yes|No|No
|ODBC Driver（支持ODBC驱动）|Yes|No|No|Yes|Yes
|Fulltext Search（支持全文搜索）|Yes|No|No|Yes|Yes
|Multi Version Concurrency（支持多版本并发控制）|Yes|No|Yes|Yes|Yes
|Footprint (jar/dll size)|（体积）|~1 MB|~2 MB|~1 MB|~4 MB|~6 MB

使用H2和HSQLDB代码基本一样是一样的，只是在添加依赖的时候不一样，修改pom.xml文件，去掉或者注释掉H2的依赖，引入HSQLDB的依赖：

```xml
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
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