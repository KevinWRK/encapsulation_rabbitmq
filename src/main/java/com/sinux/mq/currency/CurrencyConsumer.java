package com.sinux.mq.currency;

import com.rabbitmq.client.*;
import com.sinux.mq.MQAttribute;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author kevin
 * @date 2019-08-20 14:22
 */
public class CurrencyConsumer {
    public ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<String>();

    public void start(String consumerName){

        try {
            //获取实例
            MQAttribute instance = MQAttribute.getInstance();
            //获取参数map
            HashMap<String, Object> attributes = instance.attributes;
            //获取连接
            Connection connection = instance.connection;
            //创建通道
            final Channel channel = connection.createChannel();

            //声明消费者声明队列
            channel.queueDeclare((String) attributes.get(consumerName+"-queue"),
                    Boolean.parseBoolean((String) attributes.get(consumerName+"-durable")),
                    Boolean.parseBoolean((String) attributes.get(consumerName+"-exclusive")),
                    Boolean.parseBoolean((String) attributes.get(consumerName+"-auto-dele")),
                    null);

            //获取到交换机名
            String exchange = (String) attributes.get(consumerName+"-exchange");

            //获取到路由键
            String keys = (String) attributes.get(consumerName+"-routing-keys");

            //绑定交换机和路由键
            if (null != exchange && "" != exchange){
                //判断路由键数组是否为空，为空则不绑定，不为空则绑定
                if (null == keys){
                    channel.queueBind((String) attributes.get(consumerName+"-queue"),exchange,"");
                }else {
                    //剪切路由键成数组，再把所有路由键绑定
                    String[] routingKeys = keys.split(",");
                    for (String routingKey : routingKeys) {
                        channel.queueBind((String) attributes.get(consumerName+"-queue"),exchange,routingKey);
                    }
                }
            }
            //设置服务器一次发送的消息条数
            channel.basicQos(Integer.parseInt((String) attributes.get(consumerName+"-qos")));

            final Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    //把byte类型转换为字符串
                    String message = new String(body);
                    System.out.println("接收到的消息："+message);
                    //添加消息到消息队列
                    messages.offer(message);
                    //向服务器确认已接收
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            //设置消费者是否自动确认
            channel.basicConsume(
                    (String) attributes.get(consumerName+"-queue"),
                    Boolean.parseBoolean((String) attributes.get(consumerName+"-auto-ack")),
                    consumer);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
