# RabbitMQ 封装

## 前言
为了不在每一次开发中累赘重复的去写消息队列这一块，故对RabbitMQ的生产者和消费者对象的生成做了一个简易的封装

## 项目文档
[RabbitMQ 封装](http://192.168.10.61:54321/root/rabbitmq-framework.git)

## RabbitMQ在项目中的运用

### 减小程序耦合

不同进程之间传递消息时，两个进程之间的耦合程度过高，改动一个进程，引发必须修改另一个进程，为了隔离这两个进程，在两个进程间抽离出一层，所有两进程之间传递的消息，都必须通过消息队列来传递，单独修改某一个进程，不会影响另一个

### 溢出的消息进行排队处理

不同进程之间传递消息时，为了实现标准化，将消息的格式规范化了，并且，某一个线程接受的消息太多，一下无法处理完，并且也有先后顺序，必须对接受到的消息进行排队，因此诞生了消息队列

## 封装RabbitMQ的目的
每一次使用RabbitMQ都会准备大量的前缀工作，比如设置连接参数，创建连接，生成生产者和消费者对象，这些都会花费大量的时间和精力，所以干脆把这些繁杂的初始化工作给封装起来，我们只需要去修改配置文件便可以通过初始化方法获得生产者和消费者


## RabbitMQ的运行环境
在此之前，我们需要先在电脑里安装好RabbitMQ和Erlang语言的运行环境，因为RabbitMQ是基于Erlang开发的，所以要同时搭建Erlang的运行环境
我这边使用的版本是RabbitMQ3.7.17，Erlang10.4

具体的搭建过程可以参照：

[Windows 下安装RabbitMQ服务器及基本配置](https://www.cnblogs.com/vaiyanzi/p/9531607.html)

## 如何使用
首先通过配置文件配置必要的参数，然后通过配置文件中的生产者消费者名创建并获取指定的生产者和消费者对象，项目中共支持五种消息传输模式（简单模式，工作模式，发布订阅模式，路由模式和主题模式），选择要使用哪一种模式，只需在配置文件中配齐必要的参数即可

下面以XML类型的配置搭配MQ的路由模式为例

### 配置文件
首先需要在项目的Resource目录下创建配置文件，可以是XML、Properties和JSON为后缀的文件

**1.在resource目录下创建demo.xml配置文件**

![1.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/1.png
)

**2.配置连接参数**

![2.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/2.png)

**3.配置生产者参数**

![3.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/3.png)

**4.配置消费者1的参数**

![4.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/4.png)

**5.配置消费者2的参数**

![5.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/5.png)

**6.初始化配置文件**

![6.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/6.png)


**7.通过向CreateObjectTool类传入生产消费者名创建并获取配置文件配置的生产者消费者**

![7.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/7.png)


**8.生产者通过路由键发送消息给指定的队列**
![8.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/8.png)

**9.从消费者对象中获取到消息队列，并取出消息**
![9.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/9.png)


## 项目结构

项目主要分为两个包mq和parse

![10.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/10.png)


### parse包

![11.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/11.png)

parse包里面主要包含了对配置文件的解析
CenterConfig类里会扫描当前项目目录resource路径下的所有文件，如果是.xml,.properties,.json为后缀的文件，则会解析出properties和json文件中的所有键值对，xml文件中的所有标签名和内容放到Map集合中

### mq包

![12.png](https://raw.githubusercontent.com/KevinWRK/image-repository/master/rabbitmq-image/12.png)

MQAttribute类是个单例类，用来存放连接对象，配置参数的集合

CurrencyConsumer和CurrencySender是分别初始化生产者和消费者的两个类，可以根据MQAttribute单例中的配置参数生成对应的生产者和消费者

Init类是用来做初始化的，包括初始化配置参数和连接

CreateObjectTool类用来创建消费者和生产者的，可以通过传入生产消费者名来生成具体的生产消费者对象

## 缺陷及改进

目前只能支持五种模式，不支持RPC，代码还有待优化

希望能在将来支持所有的六种传输模式，并且在参数配置上能更轻量更简洁，项目结构能更清晰

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
| 工具 | 说明 | 官网 |
| ----|----|---- |
|IDEA | 开发IDE | https://www.jetbrains.com/idea/download |
