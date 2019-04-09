package com.zereao.rocketmq.controller;

import com.zereao.rocketmq.service.ConsumerService;
import com.zereao.rocketmq.service.ProducerService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zereao
 * @version 2019/04/08 16:41
 */
@RestController
public class TestController {
    private final ProducerService producerService;
    private final ConsumerService consumerService;

    public TestController(ProducerService producerService, ConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @GetMapping("send")
    public void send() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        producerService.test();
    }

    @GetMapping("xiaofei")
    public void xiaofei() throws MQClientException {
        consumerService.testXf();
    }

    @GetMapping("duobo")
    public void xiaofeiDb() throws MQClientException {
        consumerService.testDuoBo();
    }
}
