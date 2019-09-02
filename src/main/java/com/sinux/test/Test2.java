package com.sinux.test;

/**
 * @author kevin
 * @date 2019-08-19 14:52
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
//        new Init().mqInit();



        //路由模式测试
//        HashMap<String, Object> senderAndConsumer = MQAttribute.getInstance().senderAndConsumer;
//
//        RouteModelSender sender = (RouteModelSender) senderAndConsumer.get("sender");
//
//        String[] arr = {"delete","insert"};
//        sender.sendMessage("这是路由模式测试发送的消息",arr);
//        Thread.sleep(500);
//
//
//        RouteModelConsumer modelConsumer1 = (RouteModelConsumer) senderAndConsumer.get("consumer1");
//        System.out.println("这是消费者1接收的消息："+modelConsumer1.messages.poll());
//
//        RouteModelConsumer modelConsumer2 = (RouteModelConsumer) senderAndConsumer.get("consumer2");
//        System.out.println("这是消费者2接收的消息："+modelConsumer2.messages.poll());


        //订阅模式测试
//        HashMap<String, Object> senderAndConsumer = MQAttribute.getInstance().senderAndConsumer;
//
//        SubscribeModelSender sender = (SubscribeModelSender) senderAndConsumer.get("sender");
//        sender.sendMessage("这是订阅模式发送的消息！");
//
//        Thread.sleep(500);
//
//        SubscribeModelConsumer subscribeModelConsumer1 = (SubscribeModelConsumer) senderAndConsumer.get("consumer1");
//        SubscribeModelConsumer subscribeModelConsumer2 = (SubscribeModelConsumer) senderAndConsumer.get("consumer2");
//        System.out.println("消费者1接收到的消息："+subscribeModelConsumer1.messages.poll());
//        System.out.println("消费者2接收到的消息："+subscribeModelConsumer2.messages.poll());


        //工作模式
//        HashMap<String, Object> senderAndConsumer = MQAttribute.getInstance().senderAndConsumer;
//        WorkModelSender sender = (WorkModelSender) senderAndConsumer.get("sender");
//        sender.sendMessage("工作模式发送的消息1");
//        sender.sendMessage("工作模式发送的消息2");
//        sender.sendMessage("工作模式发送的消息3");
//        sender.sendMessage("工作模式发送的消息4");
//        sender.sendMessage("工作模式发送的消息5");
//
//
//        Thread.sleep(1000);
//        WorkModelConsumer workModelConsumer1 = (WorkModelConsumer) senderAndConsumer.get("consumer1");
//        ConcurrentLinkedQueue<String> messages1 = workModelConsumer1.messages;
//        WorkModelConsumer workModelConsumer2 = (WorkModelConsumer) senderAndConsumer.get("consumer2");
//        ConcurrentLinkedQueue<String> messages2 = workModelConsumer2.messages;
//        String message;
//        while ((message = messages1.poll())!= null){
//            System.out.println("消费者1捕捉到的消息："+message);
//        }
//        while ((message = messages2.poll())!= null){
//            System.out.println("消费者2捕捉到的消息："+message);
//        }


        //简单模式测试
//        HashMap<String, Object> senderAndConsumer = MQAttribute.getInstance().senderAndConsumer;
//        SimpleModelSender simpleModelSender = (SimpleModelSender) senderAndConsumer.get("sender");
//        simpleModelSender.sendMessage("发送了一条消息");
//
//        SimpleModelConsumer consumer = (SimpleModelConsumer) senderAndConsumer.get("consumer");
//        ConcurrentLinkedQueue<String> messages = consumer.messages;
//        String mess;
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        while((mess = messages.poll()) != null){
//            System.out.println("我的第一条消息："+mess);
//        }


//        String s = "delete,update,insert,select";
//        String[] split = s.split(",");
//        for (String s1 : split) {
//            System.out.println(s1);
//        }

    }
}
