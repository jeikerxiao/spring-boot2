package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * Description: 被动回复用户消息：图片消息
 * Url: https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class ImageBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content,
                                   WxMpXmlMessage wxMessage,
                                   WxMpService service) {
        WxMpXmlOutImageMessage message = WxMpXmlOutMessage.IMAGE()
                .mediaId(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }

}
