package com.sinux.mq;

import com.sinux.mq.currency.CurrencyConsumer;
import com.sinux.mq.currency.CurrencySender;

/**
 * 创建发送者和消费者的工具类
 *
 * @author kevin
 * @date 2019-08-23 12:41
 */
public class CreateObjectTool {
    public static CurrencySender getSender(String senderName){
        CurrencySender sender = new CurrencySender();

        return sender;
    }

    public static CurrencyConsumer getConsumer(String consumerName){
        CurrencyConsumer consumer = new CurrencyConsumer();
        consumer.start(consumerName);
        return consumer;
    }
}
