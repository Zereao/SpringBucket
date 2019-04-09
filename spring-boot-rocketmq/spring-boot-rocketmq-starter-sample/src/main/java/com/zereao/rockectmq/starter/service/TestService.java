package com.zereao.rockectmq.starter.service;

import com.zereao.rockectmq.starter.dto.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Zereao
 * @version 2019/04/08 16:49
 */
@Service
public class TestService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void test() {
        rocketMQTemplate.convertAndSend("StarterTopic", "convertAndSend 发送成功!");
        rocketMQTemplate.send("StarterTopic", MessageBuilder.withPayload("Send,and withPayLoad").build());
        rocketMQTemplate.convertAndSend("StarterTopic2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));
    }
}
