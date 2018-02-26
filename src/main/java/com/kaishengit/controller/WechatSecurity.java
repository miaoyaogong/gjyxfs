package com.kaishengit.controller;

import com.kaishengit.util.MessageUtil;
import com.kaishengit.util.SignUtil;
import com.kaishengit.util.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by leon on 8/2/17.
 */
@Controller
public class WechatSecurity {

    private static Logger logger = LoggerFactory.getLogger(WechatSecurity.class);
    private final Logger wechat = LoggerFactory.getLogger("wechat");
    /**
     * @Description: 用于接收 get 参数，返回验证参数
     * @param request
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "security", produces="application/json;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String doGet(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "signature", required = false) String signature,
            @RequestParam(value = "timestamp", required = false) String timestamp,
            @RequestParam(value = "nonce", required = false) String nonce,
            @RequestParam(value = "echostr", required = false) String echostr) {

        logger.debug("signature :{}, timestamp : {}, nonce : {} ,echostr : {}"
                , signature, timestamp, nonce, echostr);
        System.out.println(signature);
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
               return echostr;
            } else {
                logger.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            logger.error("非法请求");
        }
        return "不合法请求";
    }

    @RequestMapping(value = "security", produces="application/json;charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    // post 方法用于接收微信服务端消息
    public String DoPost(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("这是 post 方法！");
        try{
            Map<String, String> map= MessageUtil.parseXml(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String createTime = map.get("CreateTime");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String res = XMLParse.generate(fromUserName, toUserName, String.valueOf(new Date().getTime()), msgType, content);
            System.out.println("============================="+map.get("Content"));
            System.out.println(res);
            return res;
        }catch(Exception e){
            logger.error("解析xml失败");
        }
        return  null;
    }
}
