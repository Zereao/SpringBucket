package com.zereao.rocketmq.service;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

/**
 * @author Zereao
 * @version 2019/04/08 16:42
 */
@Service
public class ConsumerService {

    public ConsumerService(DefaultMQPushConsumer pushConsumer) {
        this.pushConsumer = pushConsumer;
    }

    private final DefaultMQPushConsumer pushConsumer;

    public void testXf() throws MQClientException {
        pushConsumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            list.forEach(msg -> System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + new String(msg.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        pushConsumer.start();
        System.out.println("------>  MQ 消息消费完毕！");
    }

    public void testDuoBo() throws MQClientException {
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 多播模式
        pushConsumer.setMessageModel(MessageModel.BROADCASTING);
        pushConsumer.subscribe("TopicTest", "TagA || TagC || TagD");
        pushConsumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            list.forEach(msg -> System.out.println(Thread.currentThread().getName() + " 多播模式 - Receive New Messages: " + new String(msg.getBody())));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        pushConsumer.start();
        System.out.println("------>  MQ 多播模式接受完毕！");
    }
}
