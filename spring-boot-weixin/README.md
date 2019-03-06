# spring-boot-weixin

Spring Boot 配合 WxJava 开发微信公众号。

* Spring Boot: 2.1.1
* weixin-java-mp: 3.3.0

调试微信服务器的工具 ngrok

https://ngrok.com/

# 说明

* 微信开发文档： https://mp.weixin.qq.com/wiki
* WxJava地址：https://github.com/Wechat-Group/WxJava


# 配置文件

application.yml

```yaml
logging:
  level:
    org.springframework.web: INFO
    com.jeiker.weixin: DEBUG
    me.chanjar.weixin: DEBUG
wx:
  mp:
    configs:
      - appId: wx732e201a20897ea3
        secret: c9d547819089dd0059bf4b39691409e1
        token: wechat
        aesKey:
```


# 配置类

WxMpConfiguration

里面最主要的两个工作

1. 注入Handler： WxMpConfiguration构造方法中
2. 初始化Services： initServices()
3. 建立路由：newRouter()

# 控制器

WxPortalController 就是对接微信服务器的入口

如在微信后台填写接口配置信息：

```
http://wx.jeiker.cn/wx/portal/wx732e201a20897ea
```

authGet() 为接口验证信息

post() 为消息接口，接收微信发送过来的消息信息，包括硬件消息，注意硬件消息无：openid

# handler文件夹下为接收消息处理器

# builder文件夹下为回复消息处理器


