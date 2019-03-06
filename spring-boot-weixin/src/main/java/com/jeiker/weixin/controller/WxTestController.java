package com.jeiker.weixin.controller;

import com.jeiker.weixin.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Description: weixin-java-mp-demo-springboot
 * User: jeikerxiao
 * Date: 2019/3/1 3:18 PM
 */
@RestController
@RequestMapping("/wx/test/{appid}")
@Slf4j
public class WxTestController {

    @GetMapping
    public String test() {
        log.info("test success!");
        return "test success!";
    }

    @PostMapping(produces = "application/xml; charset=UTF-8")
    public String test(@PathVariable String appid,
                       @RequestBody String requestBody,
                       @RequestParam(name = "signature", required = false) String signature,
                       @RequestParam(name = "timestamp", required = false) String timestamp,
                       @RequestParam(name = "nonce", required = false) String nonce,
                       @RequestParam(name = "openid", required = false) String openid,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        log.info("{}接收到微信消息：{}", appid, JsonUtils.toJson(requestBody));
        return "返回成功";
    }
}
