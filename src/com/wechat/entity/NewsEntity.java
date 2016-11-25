package com.wechat.entity;

/**
 * 图文回复实体类
 * Created by Cesiumai on 2016/9/8.
 */
public class NewsEntity {
    //消息标题
    private String Title;
    //消息描述
    private String Description;
    //消息链接
    private String Url;
    //图片链接
    private String PicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
