package com.wechat.listener;

import com.wechat.util.WechatApi;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Properties;

/**
 * 定时获取access_token
 * Created by Cesiumai on 2016/9/14.
 */
@Lazy(false)
@Component("accessTokenListener")
public class AccessTokenListener {
    @Scheduled(fixedRate = 1000 * 7 * 1000)
    public void getAccessToken() {
        InputStream inputStream = AccessTokenListener.class.getClassLoader().getResourceAsStream("wechat.properties");
        OutputStream outputStream;
        Properties properties = new Properties();
        String access_token;
        try {
            File file = new ClassPathResource("/wechat.properties").getFile();
            properties.load(inputStream);
            access_token = new WechatApi().GetAccess_token(properties.getProperty("appID"), properties.getProperty("appsecret"));
            properties.setProperty("access_token", access_token);
            outputStream = new FileOutputStream(file);
            properties.store(outputStream, "wechat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
