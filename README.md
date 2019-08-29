# RabbitMQ 封装框架

## 前言
为了不在每一次开发中累赘重复的去写消息队列这一块，故对RabbitMQ的生产者和消费者对象的生成做了一个简易的封装

## 项目文档
[RabbitMQ 封装框架](http://192.168.10.61:9601/root/rabbitmq-framework.git)

## 项目介绍
使用之前需要在resource文件夹中创建配置文件，并配置参数，文件格式可以是xml，json或properties
再实例化Init类调用mqInit()方法完成配置文件的解析和连接的创建
最后调用CreateObjectTool.getSender()和CreateObjectTool.getConsumer()方法传递生产者名和消费者名获取配置文件中配置的生产者消费者对象


## 使用技术
技术 | 说明 | 官网 
----|----|---- 
DOM4J | 用于解析XML文件 | https://dom4j.github.io/
FastJson | 用于解析JSON文档和调用JSON数据 | https://www.codeproject.com/Articles/159450/fastJSON 
Commons-io | 用于快速读取文档文件中的所有字符字段 | http://commons.apache.org/proper/commons-io/ 
AMQP | 提供统一消息服务的应用层标准高级消息队列协议 | http://www.amqp.org/


## 开发环境
| 工具 | 版本号 | 下载 |
| :----: | :----: | :----: |
| JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| RabbitMq | 3.7.14 | http://www.rabbitmq.com/download.html |


## 开发工具
工具 | 说明 | 官网 ----|----|---- IDEA | 开发IDE | https://www.jetbrains.com/idea/download 

## 参考文档
![baidu-logo](https://www.baidu.com/img/baidu_85beaf5496f291521eb75ba38eacbd87.svg)