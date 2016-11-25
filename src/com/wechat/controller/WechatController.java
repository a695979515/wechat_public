package com.wechat.controller;


import com.wechat.entity.ReceiveXmlEntity;
import com.wechat.util.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by Cesiumai on 2016/9/7.
 */
@Controller
public class WechatController {

    private static Logger logger = Logger.getLogger(WechatController.class);

    @RequestMapping(value = "/wechat", method = RequestMethod.GET)
    public void wechatDoGet(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "signature", required = true) String signature,
                            @RequestParam(value = "timestamp", required = true) String timestamp,
                            @RequestParam(value = "nonce", required = true) String nonce,
                            @RequestParam(value = "echostr", required = true) String echostr) {
        try {
            if (AttestationUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                logger.info("非微信请求！");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void wechatDoPost(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer sb = new StringBuffer();
        String xml = "";
        String result = "";
        try {
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            xml = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ReceiveXmlEntity xmlEntity = new ResolveXML().getMesEntity(xml);
        if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(xmlEntity.getMsgType())) {
            //推送事件
        } else {
            //消息处理
            result = new TulingApiProcess().getTulingResult(xmlEntity);
        }
        try {
            OutputStream os = response.getOutputStream();
            os.write(result.getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
