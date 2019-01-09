package com.jeiker.weixin.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description: 测试配置参数读取
 * User: jeikerxiao
 * Date: 2019/1/9 1:38 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WeChatAccountConfigTest {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Test
    public void getMpAppId() {
        Assert.assertNotNull(weChatAccountConfig.getMpAppId());
        log.info(weChatAccountConfig.getMpAppId());
    }

    @Test
    public void getMpAppSecret() {
        Assert.assertNotNull(weChatAccountConfig.getMpAppSecret());
        log.info(weChatAccountConfig.getMpAppSecret());
    }
}