package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMusicMessage;

/**
 * Description: 被动回复用户消息：图片消息
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class MusicBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content,
                                   WxMpXmlMessage wxMessage,
                                   WxMpService service) {
        WxMpXmlOutMusicMessage message = WxMpXmlOutMessage.MUSIC()
                .title("音乐标题")
                .description("音乐内容")
                .musicUrl("音乐连接")
                .hqMusicUrl("高质量音乐链接")
                .thumbMediaId("缩略图的媒体id")
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }
}
