package com.jeiker.weixin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 微信账号Model
 * User: jeikerxiao
 * Date: 2019/1/9 11:34 AM
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {

    private String mpAppId;
    private String mpAppSecret;
}
