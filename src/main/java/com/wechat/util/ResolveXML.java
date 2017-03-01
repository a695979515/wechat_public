package com.wechat.util;

import com.wechat.entity.ReceiveXmlEntity;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by Cesiumai on 2016/9/7.
 */
public class ResolveXML {
    public ReceiveXmlEntity getMesEntity(String xml){
        Logger logger = Logger.getLogger(ResolveXML.class);
        ReceiveXmlEntity receiveXmlEntity = null;
        if(xml.length()<=0 || xml==null){
            return null;
        }
        try {
            //将字符串转化为xml文档对象
            Document document = DocumentHelper.parseText(xml);
            //获取节点
            Element root = document.getRootElement();
            //遍历所节点下的所有子节点
            Iterator<?> iter = root.elementIterator();
            receiveXmlEntity = new ReceiveXmlEntity();
            //利用反射机制，调用set方法
            //获得该实体的元类型
            Class<?> c = Class.forName("com.wechat.entity.ReceiveXmlEntity");
            receiveXmlEntity = (ReceiveXmlEntity)c.newInstance();//创建这个实体的对象
            while(iter.hasNext()){
                Element element = (Element) iter.next();
                //获取set方法中的参数字段（实体类的属性）
                Field field = c.getDeclaredField(element.getName());
                //获取set方法，field.getType())获取它的参数数据类型
                Method method = c.getDeclaredMethod("set"+element.getName(),field.getType());
                //调用set方法
                method.invoke(receiveXmlEntity,element.getText());
            }
        } catch (DocumentException e) {
            logger.info("xml异常:"+xml);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiveXmlEntity;
    }
}
