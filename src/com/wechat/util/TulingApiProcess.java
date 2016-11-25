package com.wechat.util;

import com.wechat.entity.NewsEntity;
import com.wechat.entity.ReceiveXmlEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

//调用图灵机器人api
public class TulingApiProcess {
    public String getTulingResult(ReceiveXmlEntity xmlEntity) {
        // 此处为图灵api接口，参数key需要自己去注册申请
        String apiUrl = "http://www.tuling123.com/openapi/api?key=b7f43c0d5643fb066240c6a7bce1f9dc&info=";
        String param = "";
        try {
            param = apiUrl + URLEncoder.encode(xmlEntity.getContent(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//将参数转为url编码
        //发送httpget请求

        String result = new WechatApi().HttpGet(param);

        if (null == result) {
            return "对不起，我不能理解你说的话……";
        }
        try {
            JSONObject jsons = new JSONObject(result);
            if (100000 == jsons.getInt("code")) {//100000	文本类
             // result=new AnswerUtil().ImageAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(),"GlZhjhRDJLMirWhxQu-RCbpHRzcnVhXNTi10JfBf5vw");
                result = new AnswerUtil().TextAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), jsons.getString("text"));
            } else if (200000 == jsons.getInt("code")) {//200000	链接类
                NewsEntity newsEntity = new NewsEntity();
                newsEntity.setTitle(jsons.getString("text"));
                newsEntity.setUrl(jsons.getString("url"));
                newsEntity.setDescription(jsons.getString("text"));
                newsEntity.setPicUrl("http://panfuhao.oss-cn-shenzhen.aliyuncs.com/u=565482614,3543868459&fm=21&gp=0.jpg");
                List<NewsEntity> newsEntityList = new ArrayList<>();
                newsEntityList.add(newsEntity);
                result = new AnswerUtil().NewsAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), newsEntityList);
            } else if (302000 == jsons.getInt("code")) {//302000	新闻类
                JSONObject newjson = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(newjson.getString("list"));
                List<NewsEntity> newsEntityList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    NewsEntity newsEntity = new NewsEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    newsEntity.setTitle(jsonObject.getString("article"));
                    newsEntity.setUrl(jsonObject.getString("detailurl"));
                    newsEntity.setDescription(jsonObject.getString("source"));
                    newsEntity.setPicUrl(jsonObject.getString("icon"));
                    newsEntityList.add(newsEntity);
                }
                result = new AnswerUtil().NewsAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), newsEntityList);
            } else if (308000 == jsons.getInt("code")) {//308000	菜谱类
                JSONObject jsoninfo = new JSONObject(result);
                JSONArray jsonArray = new JSONArray(jsoninfo.getString("list"));
                List<NewsEntity> newsEntityList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    NewsEntity newsEntity = new NewsEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    newsEntity.setTitle(jsonObject.getString("name"));
                    newsEntity.setUrl(jsonObject.getString("detailurl"));
                    newsEntity.setDescription(jsonObject.getString("info"));
                    newsEntity.setPicUrl(jsonObject.getString("icon"));
                    newsEntityList.add(newsEntity);
                }
                result = new AnswerUtil().NewsAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), newsEntityList);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }


}
