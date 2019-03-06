package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutVideoMessage;

/**
 * Description: 被动回复用户消息：视频消息
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class VideoBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutVideoMessage message = WxMpXmlOutMessage.VIDEO()
                .title("视频标题")
                .description("视频内容")
                .mediaId(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }
}
