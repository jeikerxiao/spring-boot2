package com.jeiker.weixin.handler;

import com.jeiker.weixin.builder.TextBuilder;
import com.jeiker.weixin.builder.VideoBuilder;
import com.jeiker.weixin.builder.VoiceBuilder;
import com.jeiker.weixin.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.device.*;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * 默认消息处理器
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context,
                                    WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        logger.info("in MsgHandler");
        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地

            /**
             public static final String TEXT = "text";
             public static final String IMAGE = "image";
             public static final String VOICE = "voice";
             public static final String SHORTVIDEO = "shortvideo";
             public static final String VIDEO = "video";
             public static final String NEWS = "news";
             public static final String MUSIC = "music";
             public static final String LOCATION = "location";
             public static final String LINK = "link";
             public static final String EVENT = "event";
             public static final String DEVICE_TEXT = "device_text";
             public static final String DEVICE_EVENT = "device_event";
             public static final String DEVICE_STATUS = "device_status";
             public static final String HARDWARE = "hardware";
             public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
             */
            // 接收普通消息：文本消息
//            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT)) {
//                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
//            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备二维码")) {
                // 微信后台申请的产品ID
                String productId = "51625";
                WxDeviceQrCodeResult result = null;
                try {
                    result = weixinService.getDeviceService().getQrCode(productId);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备1授权")) {

                WxDevice wxDevice = new WxDevice();
                // qrticket: http://we.qq.com/d/AQCNSCO3JJRkmadK1yXy7DJEo_tkwlKR2YoJZBCA
                // deviceId
                wxDevice.setId("gh_b2e6c8d3af64_3efa69cbb7eb0e35");
                // sn: 3Q 0000 0000 1  mac: 10CEA9387D1D
                wxDevice.setMac("10CEA9387D1D");
                // 支持以下四种连接协议：
                // android classic bluetooth – 1
                // ios classic bluetooth – 2
                // ble – 3
                // wifi -- 4
                wxDevice.setConnectProtocol("3");
                // auth及通信的加密key
                wxDevice.setAuthKey("");
                // 断开策略，目前支持： 1：退出公众号页面时即断开连接 2：退出公众号之后保持连接不断开
                wxDevice.setCloseStrategy("1");
                // 连接策略 bit位可以按或置位（如1|4=5），各bit置位含义说明如下：
                // 1:（第1bit置位）在公众号对话页面，不停的尝试连接设备
                // 4:（第3bit置位）处于非公众号页面（如主界面等），微信自动连接。
                wxDevice.setConnStrategy("1");
                // auth加密方法，目前支持两种取值： 0：不加密 1：AES加密
                wxDevice.setCryptMethod("0");
                // auth version，设备和微信进行auth时，会根据该版本号来确认auth buf和auth key的格式
                wxDevice.setAuthVer("0");
                // 表示mac地址在厂商广播manufature data里含有mac地址的偏移:-1：在尾部、 -2：表示不包含mac地址
                wxDevice.setManuMacPos("-1");
                // 表示mac地址在厂商serial number里含有mac地址的偏移: -1：表示在尾部 -2：表示不包含mac地址
                wxDevice.setSerMacPos("-1");

                List<WxDevice> deviceList = new ArrayList<>();
                deviceList.add(wxDevice);

                WxDeviceAuthorize deviceAuthorize = new WxDeviceAuthorize();
                deviceAuthorize.setProductId("51625");
                // 请求操作的类型，限定取值为：0：设备授权（缺省值为0） 1：设备更新（更新已授权设备的各属性值）
                deviceAuthorize.setOpType("1");
                deviceAuthorize.setDeviceList(deviceList);
                deviceAuthorize.setDeviceNum(String.valueOf(deviceList.size()));

                WxDeviceAuthorizeResult result = null;
                try {
                    result = weixinService.getDeviceService().authorize(deviceAuthorize);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备2授权")) {

                WxDevice wxDevice = new WxDevice();
                // qrticket: http://we.qq.com/d/AQCNSCO3mQporvVuDmHm38TPLiw4Z_84U5To2X7C
                // deviceId
                wxDevice.setId("gh_b2e6c8d3af64_6d648ac452c34a1b");
                // sn: 3Q 0000 0000 2  mac: 10CEA9388553
                wxDevice.setMac("10CEA9388553");
                // 支持以下四种连接协议：
                // android classic bluetooth – 1
                // ios classic bluetooth – 2
                // ble – 3
                // wifi -- 4
                wxDevice.setConnectProtocol("3");
                // auth及通信的加密key
                wxDevice.setAuthKey("");
                // 断开策略，目前支持： 1：退出公众号页面时即断开连接 2：退出公众号之后保持连接不断开
                wxDevice.setCloseStrategy("1");
                // 连接策略 bit位可以按或置位（如1|4=5），各bit置位含义说明如下：
                // 1:（第1bit置位）在公众号对话页面，不停的尝试连接设备
                // 4:（第3bit置位）处于非公众号页面（如主界面等），微信自动连接。
                wxDevice.setConnStrategy("1");
                // auth加密方法，目前支持两种取值： 0：不加密 1：AES加密
                wxDevice.setCryptMethod("0");
                // auth version，设备和微信进行auth时，会根据该版本号来确认auth buf和auth key的格式
                wxDevice.setAuthVer("0");
                // 表示mac地址在厂商广播manufature data里含有mac地址的偏移:-1：在尾部、 -2：表示不包含mac地址
                wxDevice.setManuMacPos("-1");
                // 表示mac地址在厂商serial number里含有mac地址的偏移: -1：表示在尾部 -2：表示不包含mac地址
                wxDevice.setSerMacPos("-1");

                List<WxDevice> deviceList = new ArrayList<>();
                deviceList.add(wxDevice);

                WxDeviceAuthorize deviceAuthorize = new WxDeviceAuthorize();
                deviceAuthorize.setProductId("51625");
                // 请求操作的类型，限定取值为：0：设备授权（缺省值为0） 1：设备更新（更新已授权设备的各属性值）
                deviceAuthorize.setOpType("1");
                deviceAuthorize.setDeviceList(deviceList);
                deviceAuthorize.setDeviceNum(String.valueOf(deviceList.size()));

                WxDeviceAuthorizeResult result = null;
                try {
                    result = weixinService.getDeviceService().authorize(deviceAuthorize);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备3授权")) {

                WxDevice wxDevice = new WxDevice();
                // qrticket: http://we.qq.com/d/AQCNSCO3OlSd2BV79r-tAFQwduyGPq9CsnlRT_7s
                // deviceId
                wxDevice.setId("gh_b2e6c8d3af64_3efa69cbb7eb0e35");
                // sn: 3Q 0000 0000 3  mac: 10CEA9388206
                wxDevice.setMac("10CEA9388206");
                // 支持以下四种连接协议：
                // android classic bluetooth – 1
                // ios classic bluetooth – 2
                // ble – 3
                // wifi -- 4
                wxDevice.setConnectProtocol("3");
                // auth及通信的加密key
                wxDevice.setAuthKey("");
                // 断开策略，目前支持： 1：退出公众号页面时即断开连接 2：退出公众号之后保持连接不断开
                wxDevice.setCloseStrategy("1");
                // 连接策略 bit位可以按或置位（如1|4=5），各bit置位含义说明如下：
                // 1:（第1bit置位）在公众号对话页面，不停的尝试连接设备
                // 4:（第3bit置位）处于非公众号页面（如主界面等），微信自动连接。
                wxDevice.setConnStrategy("1");
                // auth加密方法，目前支持两种取值： 0：不加密 1：AES加密
                wxDevice.setCryptMethod("0");
                // auth version，设备和微信进行auth时，会根据该版本号来确认auth buf和auth key的格式
                wxDevice.setAuthVer("0");
                // 表示mac地址在厂商广播manufature data里含有mac地址的偏移:-1：在尾部、 -2：表示不包含mac地址
                wxDevice.setManuMacPos("-1");
                // 表示mac地址在厂商serial number里含有mac地址的偏移: -1：表示在尾部 -2：表示不包含mac地址
                wxDevice.setSerMacPos("-1");

                List<WxDevice> deviceList = new ArrayList<>();
                deviceList.add(wxDevice);

                WxDeviceAuthorize deviceAuthorize = new WxDeviceAuthorize();
                deviceAuthorize.setProductId("51625");
                // 请求操作的类型，限定取值为：0：设备授权（缺省值为0） 1：设备更新（更新已授权设备的各属性值）
                deviceAuthorize.setOpType("1");
                deviceAuthorize.setDeviceList(deviceList);
                deviceAuthorize.setDeviceNum(String.valueOf(deviceList.size()));

                WxDeviceAuthorizeResult result = null;
                try {
                    result = weixinService.getDeviceService().authorize(deviceAuthorize);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "绑定设备1")) {

                WxDeviceBind deviceBind = new WxDeviceBind();
                deviceBind.setDeviceId("gh_b2e6c8d3af64_3efa69cbb7eb0e35");
                deviceBind.setOpenId("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                WxDeviceBindResult result = null;
                try {
                    result = weixinService.getDeviceService().compelBind(deviceBind);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "绑定设备2")) {

                WxDeviceBind deviceBind = new WxDeviceBind();
                deviceBind.setDeviceId("gh_b2e6c8d3af64_6d648ac452c34a1b");
                deviceBind.setOpenId("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                WxDeviceBindResult result = null;
                try {
                    result = weixinService.getDeviceService().compelBind(deviceBind);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "绑定设备3")) {

                WxDeviceBind deviceBind = new WxDeviceBind();
                deviceBind.setDeviceId("gh_b2e6c8d3af64_4d21e64603e01886");
                deviceBind.setOpenId("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                WxDeviceBindResult result = null;
                try {
                    result = weixinService.getDeviceService().compelBind(deviceBind);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "解绑设备1")) {

                WxDeviceBind deviceBind = new WxDeviceBind();
                deviceBind.setDeviceId("gh_b2e6c8d3af64_3efa69cbb7eb0e35");
                deviceBind.setOpenId("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                WxDeviceBindResult result = null;
                try {
                    result = weixinService.getDeviceService().compelUnbind(deviceBind);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "解绑设备2")) {

                WxDeviceBind deviceBind = new WxDeviceBind();
                deviceBind.setDeviceId("gh_b2e6c8d3af64_6d648ac452c34a1b");
                deviceBind.setOpenId("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                WxDeviceBindResult result = null;
                try {
                    result = weixinService.getDeviceService().compelUnbind(deviceBind);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "解绑设备3")) {

                WxDeviceBind deviceBind = new WxDeviceBind();
                deviceBind.setDeviceId("gh_b2e6c8d3af64_4d21e64603e01886");
                deviceBind.setOpenId("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                WxDeviceBindResult result = null;
                try {
                    result = weixinService.getDeviceService().compelUnbind(deviceBind);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "用户设备")) {

                WxDeviceBindDeviceResult result = null;
                try {
                    result = weixinService.getDeviceService().getBindDevice("ojUgjt6tkI08QzSfqgzZLKgsVR24");
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备1用户")) {
                String deviceType = "gh_b2e6c8d3af64";
                String deviceId = "gh_b2e6c8d3af64_3efa69cbb7eb0e35";
                WxDeviceOpenIdResult result = null;
                try {
                    result = weixinService.getDeviceService().getOpenId(deviceType, deviceId);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备2用户")) {
                String deviceType = "gh_b2e6c8d3af64";
                String deviceId = "gh_b2e6c8d3af64_6d648ac452c34a1b";
                WxDeviceOpenIdResult result = null;
                try {
                    result = weixinService.getDeviceService().getOpenId(deviceType, deviceId);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "设备3用户")) {
                String deviceType = "gh_b2e6c8d3af64";
                String deviceId = "gh_b2e6c8d3af64_4d21e64603e01886";
                WxDeviceOpenIdResult result = null;
                try {
                    result = weixinService.getDeviceService().getOpenId(deviceType, deviceId);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(JsonUtils.toJson(result), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "文本")) {
                // 客服消息：文本
                WxMpKefuMessage message1 = WxMpKefuMessage.TEXT()
                        .toUser(wxMessage.getFromUser())
                        .content("欢迎注册测试号！")
                        .build();
                try {
                    weixinService.getKefuService().sendKefuMessage(message1);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "图片")) {
                // 客服消息：图片消息
                WxMpKefuMessage message2 = WxMpKefuMessage.IMAGE()
                        .toUser(wxMessage.getFromUser())
                        .mediaId("Ff3e3jQzaxDm-3L_K3fEwuYCUtUX5Q5SvQkwGeuUPq9ZdvvgsyjBtlmQ5_HQ8YOX")
                        .build();
                // 设置消息的内容等信息
                try {
                    weixinService.getKefuService().sendKefuMessage(message2);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "语音")) {
                // 客服消息：语音消息
                WxMpKefuMessage message2 = WxMpKefuMessage.VOICE()
                        .toUser(wxMessage.getFromUser())
                        .mediaId("EU4DDGLvCPbfVvUxk_3yRPytix2rkyFzTntpLHASsS_HIZPn5SIfzcxgPyc-pV77")
                        .build();
                try {
                    weixinService.getKefuService().sendKefuMessage(message2);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "视频")) {
                // 客服消息：视频消息
                WxMpKefuMessage message2 = WxMpKefuMessage.VIDEO()
                        .toUser(wxMessage.getFromUser())
                        .title("视频标题")
                        .description("视频描述")
                        .mediaId("v3q7VsWEtoDAQL1xR8t6nV0nKoHlJ0vDPvAyddeuf3K36wvKVhoLL02W5CiA28hB")
                        .thumbMediaId("v3q7VsWEtoDAQL1xR8t6nV0nKoHlJ0vDPvAyddeuf3K36wvKVhoLL02W5CiA28hB")
                        .build();
                try {
                    weixinService.getKefuService().sendKefuMessage(message2);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "音乐")) {
                // 客服消息：音乐消息
                WxMpKefuMessage message2 = WxMpKefuMessage.MUSIC()
                        .toUser(wxMessage.getFromUser())
                        .title("音乐标题")
                        .description("音乐描述")
                        .thumbMediaId("MEDIA_ID")
                        .musicUrl("MUSIC_URL")
                        .hqMusicUrl("HQ_MUSIC_URL")
                        .build();
                try {
                    weixinService.getKefuService().sendKefuMessage(message2);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "图文")) {
                // 客服消息：图文消息
                WxMpKefuMessage.WxArticle article1 = new WxMpKefuMessage.WxArticle();
                article1.setTitle("故宫开火锅店了");
                article1.setDescription("如今的故宫，可谓是红透半边天！");
                article1.setUrl("https://mp.weixin.qq.com/s?__biz=MzI1NTA4NDM2Mg==&mid=2651522580&idx=2&sn=6c54d0a7829cef0f006c1ab1b605dd6f&chksm=f1c4f431c6b37d27f720113c39a36a2348df6bfc8be06afa3c6cfb7ccff1f9e0c748e7076944");
                article1.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/G6xccMswqKHAJ0yrrxO2sRj90ZJ4T9icXqmnBibH7icdFuxXT4o88sRZUuSy3BWgsr7yLJKrZw4sGlGhPTX956RYw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1");

                WxMpKefuMessage.WxArticle article2 = new WxMpKefuMessage.WxArticle();
                article2.setTitle("三月，就该去斯里兰卡观鲸啊");
                article2.setDescription("在这颗蓝色的星球上，没有什么比生于大海的鲸更迷人了。");
                article2.setUrl("https://mp.weixin.qq.com/s?__biz=MjM5MzIyOTkyMA==&mid=2650390585&idx=4&sn=bf9fb6a127e112306794f1397e90400d&chksm=be975d3689e0d420b50370d35191f4075261c7b4a122b02709faadad04bdb43df5426b9b25ba");
                article2.setPicUrl("https://mmbiz.qpic.cn/mmbiz_png/C2q0IvYficGruCo1PBUXagZKiaIERIZEpicUvvKbLGzUO5hxhSYo0GsqKiaRaMEcxZibh7Ru6fI8NxdUsqRVmRIhBvQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1");

                WxMpKefuMessage message2 = WxMpKefuMessage.NEWS()
                        .toUser(wxMessage.getFromUser())
                        .addArticle(article1)
                        .addArticle(article2)
                        .build();
                try {
                    weixinService.getKefuService().sendKefuMessage(message2);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                logger.info("send news message.");
                return null;
            }
            if (wxMessage.getMsgType().equals(XmlMsgType.TEXT) &&
                    StringUtils.startsWithAny(wxMessage.getContent(), "模板消息")) {

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                        .toUser(wxMessage.getFromUser())
                        .templateId("RhczkP53ALgPKjLVN9jIMHKnoVZjGjDioyI4QBua6kY")
                        .url("https://mp.weixin.qq.com/s?__biz=MzI1NTA4NDM2Mg==&mid=2651522580&idx=2&sn=6c54d0a7829cef0f006c1ab1b605dd6f&chksm=f1c4f431c6b37d27f720113c39a36a2348df6bfc8be06afa3c6cfb7ccff1f9e0c748e7076944")
                        .build();

                templateMessage.addData(new WxMpTemplateData("first", "恭喜你购买成功！", "#0000ff"));
                templateMessage.addData(new WxMpTemplateData("name", "巧克力", "#0000ff"));
                templateMessage.addData(new WxMpTemplateData("cost", "39.8元", "#FF00FF"));
                templateMessage.addData(new WxMpTemplateData("date", dateFormat.format(new Date()), "#0000ff"));
                templateMessage.addData(new WxMpTemplateData("remark", "欢迎再次购买！", "#0000ff"));
                try {
                    String msgId = weixinService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
            // 接收普通消息：图片消息
            if (wxMessage.getMsgType().equals(XmlMsgType.IMAGE)) {
                return new TextBuilder().build(wxMessage.getPicUrl(), wxMessage, weixinService);
            }
            // 接收普通消息：语音消息
            if (wxMessage.getMsgType().equals(XmlMsgType.VOICE)) {
                return new VoiceBuilder().build(wxMessage.getMediaId(), wxMessage, weixinService);
            }
            // 接收普通消息：视频消息
            if (wxMessage.getMsgType().equals(XmlMsgType.VIDEO)) {
                return new VideoBuilder().build(wxMessage.getMediaId(), wxMessage, weixinService);
            }
            // 接收普通消息：小视频消息
            if (wxMessage.getMsgType().equals(XmlMsgType.SHORTVIDEO)) {
                return new VideoBuilder().build(wxMessage.getMediaId(), wxMessage, weixinService);
            }
            // 接收普通消息：地理位置消息
            if (wxMessage.getMsgType().equals(XmlMsgType.LOCATION)) {
                return new TextBuilder().build(wxMessage.getLabel(), wxMessage, weixinService);
            }
            // 接收普通消息：链接消息
            if (wxMessage.getMsgType().equals(XmlMsgType.LINK)) {
                return new TextBuilder().build(wxMessage.getUrl(), wxMessage, weixinService);
            }

        }

        //当用户输入关键词如“你好”，“客服”等，返回原字符串
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")) {
                return new TextBuilder().build(wxMessage.getContent(), wxMessage, weixinService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO 组装回复消息
        String content = "收到信息内容：" + JsonUtils.toJson(wxMessage);

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
