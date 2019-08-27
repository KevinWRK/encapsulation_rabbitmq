package com.sinux.mq.currency;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sinux.mq.MQAttribute;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * @author kevin
 * @date 2019-08-20 14:22
 */
public class CurrencySender {
    LinkedHashMap<String, String> attributes = null;
    Channel channel = null;
    String exchange = null;

    public CurrencySender(){
        try {
            //获取单例实例
            MQAttribute instance = MQAttribute.getInstance();
            //获取参数Map
            attributes = instance.attributes;
            //获取连接
            Connection connection = instance.connection;
            //创建通道
            channel = connection.createChannel();

            channel.queueDeclare(attributes.get("sender-queue"),
                    Boolean.parseBoolean(attributes.get("sender-durable")),
                    Boolean.parseBoolean(attributes.get("sender-exclusive")),
                    Boolean.parseBoolean(attributes.get("sender-auto-dele")),
                    null);

            //获取虚拟机的值
            exchange = attributes.get("sender-exchange");
            //判断虚拟机参数的值是否为空，为空则不配置，会报错
            if (null != exchange || "" != exchange){
                channel.exchangeDeclare(exchange,attributes.get("sender-exchange-type"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param mess
     * @param routingKeys
     */
    public void sendMessage(String mess,String[] routingKeys){
        try {
            //判断是否配置虚拟机，配置则虚拟机方式发送，没配置则队列方式发送
            if (null != exchange || "" != exchange){
                if (routingKeys.length < 1){
                    channel.basicPublish(exchange,"",null,mess.getBytes());
                }
                for (String routingKey : routingKeys) {
                    channel.basicPublish(exchange,routingKey,null,mess.getBytes());
                }
            }else {
                channel.basicPublish("",attributes.get("sender-queue"),null,mess.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
