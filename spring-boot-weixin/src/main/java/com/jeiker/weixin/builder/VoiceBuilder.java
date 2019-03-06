package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutVoiceMessage;

/**
 * Description: 被动回复用户消息：语音消息
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class VoiceBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutVoiceMessage message = WxMpXmlOutMessage.VOICE()
                .mediaId(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }
}
