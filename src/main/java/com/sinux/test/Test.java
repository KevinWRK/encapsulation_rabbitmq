package com.sinux.test;

/**
 * @author kevin
 * @date 2019-08-19 10:50
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //工作模式测试
//        WorkModelConsumer workModelConsumer = new WorkModelConsumer();
//        workModelConsumer.start("eee",1);
//
//        WorkModelSender eee = new WorkModelSender("eee");
//        eee.sendMessage("work模式发送的消息");
//
//        Thread.sleep(1000);
//
//        ConcurrentLinkedQueue<String> messages = workModelConsumer.messages;
//        String message;
//        while((message = messages.poll()) != null){
//            System.out.println(message);
//        }




        //路由测试
//        RouteModelConsumer routeModelConsumer = new RouteModelConsumer();
//        String[] rou = {"da"};
//        routeModelConsumer.start("bbb","ccc",1,rou);
//
//        RouteModelSender bbb = new RouteModelSender("bbb");
//        bbb.sendMessage("路由模式发送的消息",rou);
//
//        Thread.sleep(1000);
//
//        String mess;
//        while((mess = routeModelConsumer.messages.poll()) != null){
//            System.out.println(mess);
//        }
    }
}
