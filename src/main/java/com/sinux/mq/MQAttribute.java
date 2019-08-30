package com.sinux.mq;

import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例存放消息队列的属性
 *
 * @author kevin
 * @date 2019-08-16 13:41
 */
public class MQAttribute {
    //使用Map嵌套来保存xml,json,properties三个配置文件的配置信息
    public Map<String, Map<String,Object>> configures;
    //用一个Map集合来存放要使用的配置文件中的配置信息
    public HashMap<String,Object> attributes;
    //使用map来存放发送者和消费者
    public HashMap<String,Object> senderAndConsumer = new HashMap<String, Object>();
    //存放rabbitmq的连接对象
    public Connection connection = null;
    //使用一个map集合来存放连接数据
//    public HashMap<String,String> conParam = new HashMap<String, String>();



    private static MQAttribute mqAttribute = null;

    private MQAttribute(){}
    private static class GetMQAttribute{
        private final static MQAttribute instance = new MQAttribute();
    }
    public static MQAttribute getInstance(){
        return GetMQAttribute.instance;
    }
}
