package com.sinux.test;

import com.sinux.mq.CreateObjectTool;
import com.sinux.mq.Init;
import com.sinux.mq.currency.CurrencyConsumer;
import com.sinux.mq.currency.CurrencySender;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 通过字符串获取生产者和消费者的对象
 * @author kevin
 * @date 2019-08-22 15:35
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        new Init().mqInit();

        CurrencySender sender = CreateObjectTool.getSender("sender");
        CurrencyConsumer consumer1 = CreateObjectTool.getConsumer("consumer1");


        String[] keys = {"delete","update"};
        sender.sendMessage("直接new出来的对象发送的消息",keys);

        Thread.sleep(500);



        ConcurrentLinkedQueue<String> messages = consumer1.messages;
        System.out.println("consumer获取到的消息"+messages.poll());


    }
}
