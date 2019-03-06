package com.jeiker.weixin.handler;

import com.jeiker.weixin.builder.ImageBuilder;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 图片消息处理器
 * User: jeikerxiao
 * Date: 2019/2/28 11:45 AM
 */
@Component
public class ImageHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        if (wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.IMAGE)) {
            //TODO 可以选择将消息保存到本地
            return new ImageBuilder().build(wxMpXmlMessage.getMediaId(), wxMpXmlMessage, wxMpService);
        }
        return null;
    }
}
