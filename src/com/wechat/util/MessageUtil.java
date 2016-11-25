package com.wechat.util;

/**
 * Created by Cesiumai on 2016/9/7.
 * 微信消息工具类
 */
public class MessageUtil {
    /**
     * 接收文本消息
     */
    public static final String RESP_MESSAGE_TYPE_TEXT="text";
    /**
     * 接收图文消息
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE="image";
    /**
     * 接收语音消息
     */
    public static final String RESP_MESSAGE_TYPE_VOICE="voice";
    /**
     * 接收视频消息
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO="video";
    /**
     * 接收小视频消息
     */
    public static final String RESP_MESSAGE_TYPE_SHORTVIDEO="shortvideo";
    /**
     * 接收地理位置消息
     */
    public static final String RESP_MESSAGE_TYPE_LOCATION="location";
    /**
     * 接收链接信息
     */
    public static final String RESP_MESSAGE_TYPE_LINK="link";

    /**
     * 事件-关注事件
     */
    public static final String EVENT_TYPE_SUBSCRIBE="subscribe";
    /**
     * 事件-取消关注事件
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE ="unsubscribe";
    /**
     * 事件-用户已关注时的事件推送
     */
    public static final String EVENT_TYPE_SCAN ="SCAN";
    /**
     * 事件-上报地理位置事件
     */
    public static final String EVENT_TYPE_LOCATION ="LOCATION";
    /**
     * 事件-点击菜单拉取消息时的事件推送
     */
    public static final String EVENT_TYPE_CLICK ="CLICK";
    /**
     * 事件-点击菜单跳转链接时的事件推送
     */
    public static final String EVENT_TYPE_VIEW ="VIEW";


    /**
     * 回复文本消息
     */
    public static final String REQ_MESSAGE_TYPE_TEXT="text";
    /**
     * 回复图片消息
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE="image";
    /**
     * 回复语言信息
     */
    public static final String REQ_MESSAGE_TYPE_VOICE="voice";
    /**
     * 回复视频消息
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO="video";
    /**
     * 回复音乐消息
     */
    public static final String REQ_MESSAGE_TYPE_MUSIC="music";
    /**
     * 回复图文消息
     */
    public static final String REQ_MESSAGE_TYPE_NEWS="news";
    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

}
