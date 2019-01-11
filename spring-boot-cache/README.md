# spring-boot-cache

Spring Boot 整合 cache。


Spring Cache 对 Cahce 进行了抽象，提供了 @Cacheable、@CachePut、@CacheEvict 等注解。

Spring Boot 应用基于 Spring Cache，既提供了基于内存实现的缓存管理器，可以用于单体应用系统，也集成了 EhCache、Redis 等缓存服务器，可以用于大型系统或者分布式系统。

# 说明

## 注解说明

* `@CachePut`: 这个注解直接将返回值放入缓存中，通常用于保存和修改方法中

* `@Cacheable`: 这个注解在执行前先查看缓存中是不是已经存在了，如果存在，直接返回。如果不存在，将方法的返回值放入缓存。

* `@CacheEvict`: 这个注解在执行方法执行成功后会从缓存中移除,allEntries熟悉，默认为false，true的时候移除所有缓存。


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

