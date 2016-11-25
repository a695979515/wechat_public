package com.wechat.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/**
 * Created by Cesiumai on 2016/9/7.
 * 判断请求是否来源于微信工具类
 */
public class AttestationUtil {
    public static final String token="echostr";
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        //将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
        }
        //将三个参数字符串拼接成一个字符串进行sha1加密
        String sha_1 = DigestUtils.sha1Hex(stringBuilder.toString());
        //获得加密后的字符串可与signature对比，标识该请求来源于微信
        return stringBuilder != null ? signature.equals(sha_1) : false;
    }
}
