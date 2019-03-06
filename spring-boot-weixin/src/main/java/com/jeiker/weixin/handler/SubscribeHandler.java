package com.jeiker.weixin.handler;

import com.jeiker.weixin.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 关注处理器
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context,
                                    WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库

                // 客服消息：文本
                WxMpKefuMessage message1 = WxMpKefuMessage.TEXT()
                        .toUser(wxMessage.getFromUser())
                        .content("欢迎注册测试号！")
                        .build();
                weixinService.getKefuService().sendKefuMessage(message1);

                // 客服消息：图片消息
                WxMpKefuMessage message2 = WxMpKefuMessage.IMAGE()
                        .toUser(wxMessage.getFromUser())
                        .mediaId("Ff3e3jQzaxDm-3L_K3fEwuYCUtUX5Q5SvQkwGeuUPq9ZdvvgsyjBtlmQ5_HQ8YOX")
                        .build();
                // 设置消息的内容等信息
                weixinService.getKefuService().sendKefuMessage(message2);
                // 客服消息：语音消息
                WxMpKefuMessage message3 = WxMpKefuMessage.VOICE()
                        .toUser(wxMessage.getFromUser())
                        .mediaId("EU4DDGLvCPbfVvUxk_3yRPytix2rkyFzTntpLHASsS_HIZPn5SIfzcxgPyc-pV77")
                        .build();
                weixinService.getKefuService().sendKefuMessage(message3);

                // 客服消息：视频消息
                WxMpKefuMessage message4 = WxMpKefuMessage.VIDEO()
                        .toUser(wxMessage.getFromUser())
                        .title("视频标题")
                        .description("视频描述")
                        .mediaId("MEDIA_ID")
                        .thumbMediaId("MEDIA_ID")
                        .build();
                weixinService.getKefuService().sendKefuMessage(message4);

                // 客服消息：音乐消息
                WxMpKefuMessage message5 = WxMpKefuMessage.MUSIC()
                        .toUser(wxMessage.getFromUser())
                        .title("音乐标题")
                        .description("音乐描述")
                        .thumbMediaId("MEDIA_ID")
                        .musicUrl("MUSIC_URL")
                        .hqMusicUrl("HQ_MUSIC_URL")
                        .build();
                weixinService.getKefuService().sendKefuMessage(message5);

                // 客服消息：图文消息
                WxMpKefuMessage.WxArticle article1 = new WxMpKefuMessage.WxArticle();
                article1.setTitle("程序员找工作黑名单");
                article1.setDescription("程序员找工作黑名单 by shengxinjing 分享自 @非著名程序员 开通的独家号《非著名程序员》");
                article1.setUrl("http://mmbiz.qpic.cn/mmbiz_jpg/oQ8G3qrDibqem7XiazkWaT3W5xGS0qYvtmtnL9PhHxtJyOSzqGslKxQb06iaEkdldiaMIh1MaGf8Th3G0WOJxibsFTQ/0");
                article1.setPicUrl("https://toutiao.io/posts/cvyxl0");

                WxMpKefuMessage.WxArticle article2 = new WxMpKefuMessage.WxArticle();
                article2.setTitle("程序员找工作黑名单");
                article2.setDescription("程序员找工作黑名单 by shengxinjing 分享自 @非著名程序员 开通的独家号《非著名程序员》");
                article2.setUrl("http://mmbiz.qpic.cn/mmbiz_jpg/oQ8G3qrDibqem7XiazkWaT3W5xGS0qYvtmtnL9PhHxtJyOSzqGslKxQb06iaEkdldiaMIh1MaGf8Th3G0WOJxibsFTQ/0");
                article2.setPicUrl("https://toutiao.io/posts/cvyxl0");

                WxMpKefuMessage message6 = WxMpKefuMessage.NEWS()
                        .toUser(wxMessage.getFromUser())
                        .addArticle(article1)
                        .addArticle(article2)
                        .build();
                weixinService.getKefuService().sendKefuMessage(message6);
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
            throws Exception {
        //TODO
        return null;
    }

}
