package com.sinux.mq;

import com.rabbitmq.client.ConnectionFactory;
import com.sinux.parse.base.CenterConfig;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.HashMap;

/**
 * 初始化方法
 * @author kevin
 * @date 2019-08-19 16:55
 */
public class Init {
    HashMap<String,Object> attribute;
    public void mqInit(){
        //解析配置文件
        new CenterConfig().parseFile();
        //选择要使用哪一个配置文件的配置
        MQAttribute.getInstance().attributes = (HashMap<String, Object>) MQAttribute.getInstance().configures.get("demo.xml");
        attribute = MQAttribute.getInstance().attributes;



        //加载配置文件
        //parseXML(Init.class,"demo.xml");



        //初始化连接到连接池队列中
        initConnection();
        //把必要的参数佩奇
        //autoConfig();


        HashMap<String,Object> senderAndConsumer = MQAttribute.getInstance().senderAndConsumer;
        //获取消费者人数
//        Integer conNum = Integer.valueOf((String) attribute.get("consumer-num"));


//        CurrencySender currencySender = new CurrencySender();
//        for(int i =1;i<=conNum;i++){
//            String consumerName = "consumer"+i;
//            CurrencyConsumer consumer = new CurrencyConsumer();
//            consumer.start(consumerName);
//            //添加消费者对象到单例集合
//            senderAndConsumer.put(consumerName,consumer);
//        }
//        //添加发送者对象到单例集合
//        senderAndConsumer.put("sender",currencySender);


    }


    /**
     * 初始化连接
     */
    public void initConnection(){


        try {
            //创建连接工厂对象
            ConnectionFactory factory = new ConnectionFactory();
            //绑定参数

            factory.setVirtualHost((String) attribute.get("virtualHost"));
            factory.setUsername((String) attribute.get("username"));
            factory.setPassword((String) attribute.get("password"));
            factory.setHost((String) attribute.get("host"));
            factory.setPort(Integer.parseInt(String.valueOf(attribute.get("port"))));
            //将生成的连接对象放到单例中
            MQAttribute.getInstance().connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








    /**
     * 解析xml文件的方法
     * @param c
     * @param xml
     * @throws DocumentException
     */
//    public void parseXML(Class<?> c,String xml){
//
//        try {
//            //解析xml中的数据
//            SAXReader reader = new SAXReader();
//            //通过加载classpath下的xml文件来获取document对象
//            Document document = null;
//            document = reader.read(c.getClassLoader().getResourceAsStream(xml));
//            //将文件下的第一节点元素交给parseElement解析
//            parseElement(document.getRootElement());
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 解析每一个结点元素
//     * @param rootElement
//     */
//    public void parseElement(Element rootElement){
//        Element element;
//        //获取节点元素里的所有的子节点元素，并放到迭代器里面迭代,判断条件为迭代器里面是否还有下一个元素
//        Iterator<?> iterator = rootElement.elementIterator();
//        while (iterator.hasNext()){
//            //获取子节点元素的对象
//            element = (Element) iterator.next();
//
//            if ("connection" == element.getName()){
//                getConnectionParam(element);
//            }
//
//            //判断当前节点是否为consumer,是则去获取他的属性和值
//            if ("consumer" == element.getName()){
//                getNum(element);
//            }
//
//            MQAttribute.getInstance().attributes.put(element.getName(),element.getTextTrim());
//            //判断是否为节点
//            if (element.getNodeType() == Node.ELEMENT_NODE){
//                //判断节点里是否还有内容
//                if (element.hasContent()){
//                    parseElement(element);
//                }
//            }
//        }
//    }

    /**
     * 从xml中获取连接的参数
     * @param e
     */
//    public void getConnectionParam(Element e){
//        Iterator iterator = e.elementIterator();
//        while(iterator.hasNext()){
//            Element element = (Element) iterator.next();
//            MQAttribute.getInstance().conParam.put(element.getName(),element.getTextTrim());
//        }
//    }

    /**
     * 从xml中获取属性名和属性值
     * @param e
     */
//    public void getNum(Element e){
//        Iterator iterator = e.attributeIterator();
//        while(iterator.hasNext()){
//            Attribute attribute = (Attribute)iterator.next();
//            MQAttribute.getInstance().attributes.put(attribute.getName(),attribute.getValue());
//        }
//    }

    /**
     * 通过反射初始化连接
     */
//    public void initConnectionReflex(){
//
//        try {
//            //创建连接工厂对象
//            ConnectionFactory factory = new ConnectionFactory();
//            //获取单例里的连接参数Map
//            HashMap<String, Object> conParam = MQAttribute.getInstance().conParam;
//            //获取所有连接参数中的key
//            Set<String> strings = conParam.keySet();
//            //获取工厂对象的反射类
//            Class<? extends ConnectionFactory> cla = factory.getClass();
//
//
//            for (String string : strings) {
//                //把头字母小写转换为大写
//                char[] chars = string.toCharArray();
//                chars[0] = (char) (chars[0] - 32);
//                String methodSuf = String.valueOf(chars);
//                //因为setPort方法的参数类型是int，其他参数类型是String,所以进行一次判断
//                //不然会发生找不到method的错误
//                if (!(methodSuf.equals("Port"))){
//                    Method method = cla.getMethod("set".concat(methodSuf), String.class);
//                    method.invoke(factory,conParam.get(string));
//                }else {
//                    Method method = cla.getMethod("set".concat(methodSuf), int.class);
//                    method.invoke(factory,Integer.parseInt(conParam.get(string)));
//                }
//
//
//            }
//            //生成连接对象并放入单例存储
//            Connection connection = factory.newConnection();
//            MQAttribute.getInstance().connection = connection;
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 自动配齐必要参数的方法
     * 需要配齐的参数有
     * qos 默认配置1
     * durable,exclusive,autoDele,autoAck默认配置false
     */
//    public void autoConfig(){
//        LinkedHashMap<String, String> attributes = MQAttribute.getInstance().attributes;
//        //获取消费者人数
//        int ConNum = Integer.parseInt(attributes.get("consumer-num"));
//
//        //初始化发送者的配置
//        if (null == attributes.get("sender-durable") || ""==attributes.get("sender-durable")){
//            attributes.put("sender-durable","false");
//        }
//        if (null == attributes.get("sender-exclusive") || ""==attributes.get("sender-exclusive")){
//            attributes.put("sender-exclusive","false");
//        }
//        if (null == attributes.get("sender-autoDele") || ""==attributes.get("sender-autoDele")){
//            attributes.put("sender-autoDele","false");
//        }
//        if (null == attributes.get("sender-qos") || ""==attributes.get("sender-qos")){
//            attributes.put("sender-qos","1");
//        }
//
//
//        //初始化消费者的配置
//        for (int i=1;i<=ConNum;i++){
//            String conName = "consumer"+i;
//
//            if (null == attributes.get(conName+"-durable") || ""==attributes.get(conName+"-durable")){
//                attributes.put(conName+"-durable","false");
//            }
//            if (null == attributes.get(conName+"-exclusive") || ""==attributes.get(conName+"-exclusive")){
//                attributes.put(conName+"-exclusive","false");
//            }
//            if (null == attributes.get(conName+"-autoDele") || ""==attributes.get(conName+"-autoDele")){
//                attributes.put(conName+"-autoDele","false");
//            }
//            if (null == attributes.get(conName+"-auto-ack") || ""==attributes.get(conName+"-auto-ack")){
//                attributes.put(conName+"-autoDele","false");
//            }
//            if (null == attributes.get(conName+"-qos") || ""==attributes.get(conName+"-qos")){
//                attributes.put(conName+"-qos","1");
//            }
//        }
//    }
}
