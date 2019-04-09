package com.zereao.rockectmq.starter.service.consume;

import com.zereao.rockectmq.starter.dto.OrderPaidEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author Zereao
 * @version 2019/04/08 14:07
 */
@Service
@RocketMQMessageListener(topic = "StarterTopic2", consumerGroup = "my-consumer_test-topic-2")
public class OrderPaidEventConsumer implements RocketMQListener<OrderPaidEvent> {
    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        System.out.println("-----------> consumerGroup = my-consumer_test-topic-2 , result = " + orderPaidEvent.toString());
    }
}
