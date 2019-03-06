package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * Description: 被动回复用户消息：文本消息
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class TextBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content,
                                   WxMpXmlMessage wxMessage,
                                   WxMpService service) {
        WxMpXmlOutTextMessage message = WxMpXmlOutMessage.TEXT()
                .content(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }

}
