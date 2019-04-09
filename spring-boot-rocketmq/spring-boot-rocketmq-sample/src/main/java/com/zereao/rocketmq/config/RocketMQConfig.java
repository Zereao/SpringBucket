package com.zereao.rocketmq.config;

import lombok.Data;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zereao
 * @version 2019/04/03 15:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "apache.rocketmq")
public class RocketMQConfig {
    private String nameServerAddress;

    @Bean(initMethod = "start")
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer("rocketmq-cluster-SyncProducer");
        producer.setNamesrvAddr(nameServerAddress);
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("rocketmq-cluster");
        pushConsumer.setNamesrvAddr(nameServerAddress);
        pushConsumer.subscribe("TopicTest", "*");
        return pushConsumer;
    }
}
