package com.jeiker.weixin.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTransferKefuMessage;

/**
 * Description: 被动回复用户消息：图片消息
 * User: jeikerxiao
 * Date: 2019/2/28 1:42 PM
 */
public class TransferCustomerServiceBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutTransferKefuMessage message = WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                .kfAccount(content)
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .build();
        return message;
    }
}
