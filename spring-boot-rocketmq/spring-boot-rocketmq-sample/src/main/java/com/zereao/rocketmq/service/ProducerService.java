package com.zereao.rocketmq.service;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author Zereao
 * @version 2019/04/09 09:55
 */
@Service
public class ProducerService {
    private final DefaultMQProducer producer;

    public ProducerService(DefaultMQProducer producer) {this.producer = producer;}

    public void test() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message msg = new Message("TopicTest", "TagA", "OrderID188", "这是一条MQ 同步消息".getBytes(StandardCharsets.UTF_8));
        SendResult result = producer.send(msg);
        System.out.printf("------>  MQ 同步消息发送完毕！  result = %s\n", result.toString());

        msg = new Message("TopicTest", "TagA", "OrderID189", "这是一条MQ异步消息".getBytes(StandardCharsets.UTF_8));
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("------>  MQ 异步消息发送完毕！  result = %s\n", sendResult.toString());
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.printf("------>  MQ 异步消息发送异常！  result = %s\n", throwable.toString());
            }
        });

        msg = new Message("TopicTest", "TagA", "OrderID190", "这是一条MQ 单项传输消息".getBytes(StandardCharsets.UTF_8));
        producer.sendOneway(msg);
        System.out.println("------>  MQ 单项传输消息发送完毕！");
    }
}
