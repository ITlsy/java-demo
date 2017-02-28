package com.lsy.controller;

import com.lsy.service.WeiXinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/2/25 0025.
 */
@Controller
@RequestMapping("/wx")
public class WeiXinController {
    private Logger logger= LoggerFactory.getLogger(WeiXinController.class);
    @Autowired
    private WeiXinService weiXinService;

    //微信初始化
    @GetMapping("/init")
    @ResponseBody
    public String init(String msg_signature,String timestamp,String nonce,String echostr){
        logger.info("{}-{}-{}",msg_signature,timestamp,nonce,echostr);
        return weiXinService.init(msg_signature, timestamp, nonce, echostr);

    }

}
