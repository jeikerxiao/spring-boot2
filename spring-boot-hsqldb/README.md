

# spring-boot-activemq

Spring Boot 配合 ActiveMQ。

# 说明

网上偷来两张说明图片。

消息队列模式 (demo1示例)

![image](../images/activemq1.png)



发布、订阅模式 (demo2示例)

![image](../images/activemq2.png)

## 项目配置


application.properties文件中配置 ActiveMQ:

```java

spring.activemq.broker-url=tcp://192.168.235.32:61616
spring.activemq.user=admin
spring.activemq.password=admin
spring.activemq.pool.enabled=false
#spring.jms.pub-sub-domain=true
```

## 消息队列模式

demo1

### 生产者

```java

@Service
public class Producer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(String destinationName, String message) {
        System.out.println("============>>>>> 发送queue消息 " + message);
        Destination destination = new ActiveMQQueue(destinationName);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}
```

### 消费者

```java
@Service
public class Consumer {

    @JmsListener(destination = "test.queue")
    public void receiveMsg(String text) {
        System.out.println("<<<<<<============ 收到消息： " + text);
    }
}

```

## 发布、订阅模式

demo2

### 发布者

```java

@Service
public class Publisher {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void publish(String destinationName, String message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("============>>>>> 发布topic消息 " + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}

```

### 订阅者

```java

@Service
public class Subscriber {

    @JmsListener(destination = "test.topic", containerFactory = "myJmsContainerFactory")
    public void subscribe(String text) {
        System.out.println("===========<<<<<<<<收到订阅的消息" + text);
    }
}

```


## 运行测试用例

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootActivemqApplicationTests {

	@Resource
	private Producer producer;
	@Resource
	private Publisher publisher;

	@Test
	public void testQueue() {
		for (int i = 0; i < 10; i++) {
			producer.sendMsg("test.queue", "Queue Message " + i);
		}
	}

	@Test
	public void testTopic() {
		for (int i = 0; i < 10; i++) {
			publisher.publish("test.topic", "Topic Message " + i);
		}
	}
}
```


