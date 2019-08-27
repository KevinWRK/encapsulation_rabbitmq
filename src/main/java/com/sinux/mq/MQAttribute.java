package com.sinux.mq;

import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 单例存放消息队列的属性
 *
 * @author kevin
 * @date 2019-08-16 13:41
 */
public class MQAttribute {
    //用一个Map集合来存放读取到xml的属性信息
    public LinkedHashMap<String,String> attributes = new LinkedHashMap<String, String>();
    //使用map来存放发送者和消费者
    public HashMap<String,Object> senderAndConsumer = new HashMap<String, Object>();
    //存放rabbitmq的连接对象
    public Connection connection = null;
    //使用一个map集合来存放连接数据
    public HashMap<String,String> conParam = new HashMap<String, String>();

    private static MQAttribute mqAttribute = null;

    private MQAttribute(){}
    private static class GetMQAttribute{
        private final static MQAttribute instance = new MQAttribute();
    }
    public static MQAttribute getInstance(){
        return GetMQAttribute.instance;
    }
}
