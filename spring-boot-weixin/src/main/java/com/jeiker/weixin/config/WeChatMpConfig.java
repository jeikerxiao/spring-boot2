package com.jeiker.weixin.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Description: 微信配置
 * User: jeikerxiao
 * Date: 2019/1/9 11:36 AM
 */
@Component
public class WeChatMpConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getMpAppId());
        wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getMpAppSecret());
        return wxMpInMemoryConfigStorage;
    }

}
