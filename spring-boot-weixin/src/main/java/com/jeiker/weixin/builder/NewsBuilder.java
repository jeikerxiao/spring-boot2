package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 被动回复用户消息：图文消息
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class NewsBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        // 图文消息
        List<WxMpXmlOutNewsMessage.Item> itemList = new ArrayList<>();
        WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
        item.setTitle("程序员找工作黑名单");
        item.setDescription("程序员找工作黑名单 by shengxinjing 分享自 @非著名程序员 开通的独家号《非著名程序员》");
        item.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/oQ8G3qrDibqem7XiazkWaT3W5xGS0qYvtmtnL9PhHxtJyOSzqGslKxQb06iaEkdldiaMIh1MaGf8Th3G0WOJxibsFTQ/0");
        item.setUrl("https://toutiao.io/posts/cvyxl0");

        WxMpXmlOutNewsMessage message = WxMpXmlOutMessage.NEWS()
                .articles(itemList)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }
}
