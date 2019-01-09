# spring-boot-h2

Spring Boot 配合 H2。

# 说明



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