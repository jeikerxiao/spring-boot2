package com.jeiker.weixin.handler;

import com.jeiker.weixin.builder.TextBuilder;
import com.jeiker.weixin.consts.DeviceConst;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Description: 设备消息处理器
 * User: jeikerxiao
 * Date: 2019/2/28 5:02 PM
 */
@Component
public class DeviceHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) throws WxErrorException {
        logger.info("device message");
        // 接收设备绑定解绑事件
        if (wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.DEVICE_EVENT)) {
            logger.info("device message: device_enent");
            // 绑定事件
            if (wxMpXmlMessage.getEvent().equals(DeviceConst.EventType.BIND)) {
                return new TextBuilder().build("绑定设备:" + wxMpXmlMessage.getDeviceId(), wxMpXmlMessage, wxMpService);
            }
            // 解绑事件
            if (wxMpXmlMessage.getEvent().equals(DeviceConst.EventType.UNBIND)) {
                return new TextBuilder().build("解绑设备:" + wxMpXmlMessage.getDeviceId(), wxMpXmlMessage, wxMpService);
            }
            String content = "设备消息：[DeviceID]" + wxMpXmlMessage.getDeviceId()
                    + " [Content]" + wxMpXmlMessage.getContent();
            return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
        }
        // 接收设备消息
        if (wxMpXmlMessage.getMsgType().equals(WxConsts.XmlMsgType.DEVICE_TEXT)) {
            logger.info("device message: device_text");

            String deviceContent = wxMpXmlMessage.getContent();
            // 解析微信发送过来的设备数据

            String content = "device message：[DeviceID]" + wxMpXmlMessage.getDeviceId()
                    + " [Content]" + wxMpXmlMessage.getContent();
            return new TextBuilder().build(content, wxMpXmlMessage, wxMpService);
        }
        return null;
    }

    private void sendGlucoseResultTemplate(WxMpXmlMessage wxMessage,
                                           WxMpService weixinService,
                                           Double value,
                                           Date date
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(wxMessage.getFromUser())
                .templateId("tbqhfA_gPd00Fyfwp3CwLU1pl8zmPqu009x-hGxS0Xk")
                .url("https://mp.weixin.qq.com/s?__biz=MzI1NTA4NDM2Mg==&mid=2651522580&idx=2&sn=6c54d0a7829cef0f006c1ab1b605dd6f&chksm=f1c4f431c6b37d27f720113c39a36a2348df6bfc8be06afa3c6cfb7ccff1f9e0c748e7076944")
                .build();
        templateMessage.addData(new WxMpTemplateData("value", value.toString(), "#0000ff"));
        templateMessage.addData(new WxMpTemplateData("time", dateFormat.format(date), "#0000ff"));
        try {
            String msgId = weixinService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    private void sendUaResultTemplate(WxMpXmlMessage wxMessage,
                                      WxMpService weixinService,
                                      Double value,
                                      Date date
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(wxMessage.getFromUser())
                .templateId("FYH38dHhSnDqSuW1Uoqaun8V8V2Q_IL1H4nPHyl-vI0")
                .url("https://mp.weixin.qq.com/s?__biz=MzI1NTA4NDM2Mg==&mid=2651522580&idx=2&sn=6c54d0a7829cef0f006c1ab1b605dd6f&chksm=f1c4f431c6b37d27f720113c39a36a2348df6bfc8be06afa3c6cfb7ccff1f9e0c748e7076944")
                .build();
        templateMessage.addData(new WxMpTemplateData("value", value.toString(), "#0000ff"));
        templateMessage.addData(new WxMpTemplateData("time", dateFormat.format(date), "#0000ff"));
        try {
            String msgId = weixinService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    private void sendConnectTemplate(WxMpXmlMessage wxMessage,
                                     WxMpService weixinService,
                                     Boolean isGlucose
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(wxMessage.getFromUser())
                .templateId("WEefgimTQqtyNWDn5zMwK1IeHW5ThFYwPGj-R4IT-vk")
                .url("https://mp.weixin.qq.com/s?__biz=MzI1NTA4NDM2Mg==&mid=2651522580&idx=2&sn=6c54d0a7829cef0f006c1ab1b605dd6f&chksm=f1c4f431c6b37d27f720113c39a36a2348df6bfc8be06afa3c6cfb7ccff1f9e0c748e7076944")
                .build();
        templateMessage.addData(new WxMpTemplateData("status", "已连接", "#0000ff"));
        templateMessage.addData(new WxMpTemplateData("date", dateFormat.format(new Date()), "#0000ff"));
        if (isGlucose) {
            templateMessage.addData(new WxMpTemplateData("remark", "请开始测量血糖", "#0000ff"));
        } else {
            templateMessage.addData(new WxMpTemplateData("remark", "请开始测量尿酸", "#0000ff"));

        }
        try {
            String msgId = weixinService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
