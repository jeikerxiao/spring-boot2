package com.jeiker.weixin.handler;

import com.jeiker.weixin.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 扫码处理器
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ScanHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理
        return new TextBuilder().build("用户扫码", wxMpXmlMessage, wxMpService);
    }
}
