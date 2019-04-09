package com.zereao.rockectmq.starter.service.consume;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Zereao
 * @version 2019/04/08 14:04
 */
@Service
@RocketMQMessageListener(topic = "StarterTopic", consumerGroup = "my-consumer_test-topic-1")
public class StringConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("--------> consumerGroup = my-consumer_test-topic-1 , result = " + s);
    }
}
