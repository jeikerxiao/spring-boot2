
# SpringBoot 配置文件敏感信息加密

## 说明

使用过SpringBoot配置文件的朋友都知道，资源文件中的内容通常情况下是明文显示，安全性就比较低一些。

打开`application.properties`或`application.yml`，比如 `MySql`登陆密码，`Redis`登陆密码以及第三方的密钥等等一览无余，这里介绍一个加解密组件，提高一些属性配置的安全性。

`jasypt`由一个国外大神写了一个springboot下的工具包，用来加密配置文件中的信息。


## 数据用户名和数据库密码加密为例

### 1. 引入包

查看最新版本可以到:

https://github.com/ulisesbocchio/jasypt-spring-boot

```xml
<dependency>
        <groupId>com.github.ulisesbocchio</groupId>
        <artifactId>jasypt-spring-boot-starter</artifactId>
        <version>2.1.0</version>
</dependency>
```

### 2. 配置加/解的密码

```java
# jasypt加密的密匙
jasypt:
  encryptor:
    password: Y6M9fAJQdU7jNp5MW
```



### 3. 测试用例中生成加密后的秘钥

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void getPass() {
        String url = encryptor.encrypt("jdbc:mysql://localhost:3306/mydb?autoReconnect=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("123456");
        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
        Assert.assertTrue(url.length() > 0);
        Assert.assertTrue(name.length() > 0);
        Assert.assertTrue(password.length() > 0);
    }
}
```

下面是输出加密字符串：

```bash
database url: 6Ut7iADnHS18cManoFJuNRQ5QEDfcho/F96SOhsHZdXlHYCa5PSrz6rk48I9eHB7qPp5AxDFBk9xi0I1hi6BJ0DSPYA9443gBAk5JDUxDufjUKsdh6knZJLNELmFJzYrDvCu4S0x22MYdZqJDLbyDUU2JcoezCvs156vmsPgU4A=
database name: fmai72yGYKGlP6vTtX77EQ==
database password: GPMG7FGV+EA9iGkC27u67A==
```

### 4. 将加密后的字符串替换原明文

applicatioin.yml

```bash
server:
  port: 8080
spring:
  # 数据库相关配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 这里加上后缀用来防止mysql乱码,serverTimezone=GMT%2b8设置时区
    url: ENC(h20YiPrvNnuuTGjlrE1RVpudMuIQAS6ZPSVo1SPiYVyLen7/TWI5rXVRkStA3MDcoVHQCmLa70wYU6Qo8wwtnsmaXa5jykD3MNhAp5SGJxHsTG5u7tflPdnNmOufyhdsYPxBGWAgibYs9R7yBfrvtwBTRbe096APd3bnG3++Yro=)
    username: ENC(sT6BztXbJEa71eg3pPGYMQ==)
    password: ENC(MpSZFJ9ftq+3+VUANZjr0Q==)
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  # 返回的api接口的配置，全局有效
  jackson:
   # 如果某一个字段为null，就不再返回这个字段
    default-property-inclusion: non_null
    date-format: yyyy-MM-dd HH:mm:ss
    serialization:
      write-dates-as-timestamps: false
    time-zone: GMT+8
# jasypt加密的密匙
jasypt:
  encryptor:
    password: Y6M9fAJQdU7jNp5MW
```

> 注意: 上面的 `ENC()` 是固定写法.


### 附言


### 部署时配置salt(盐)值

为了防止salt(盐)泄露,反解出密码.可以在项目部署的时候使用命令传入salt(盐)值:

```bash
java -jar xxx.jar  -Djasypt.encryptor.password=Y6M9fAJQdU7jNp5MW
```

或者在服务器的环境变量里配置,进一步提高安全性.

打开`/etc/profile`文件

```
vim /etc/profile
```

在`profile`文件末尾插入salt(盐)变量

```bash
export JASYPT_PASSWORD = Y6M9fAJQdU7jNp5MW
```

编译，使配置文件生效 

```
source /etc/profile
```

运行 

```bash
java -jar -Djasypt.encryptor.password=${JASYPT_PASSWORD} xxx.jar
```

