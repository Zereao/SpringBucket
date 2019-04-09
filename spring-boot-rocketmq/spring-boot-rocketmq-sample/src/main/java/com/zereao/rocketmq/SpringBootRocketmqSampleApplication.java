package com.zereao.rocketmq;

import com.zereao.rocketmq.config.RocketMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RocketMQConfig.class})
public class SpringBootRocketmqSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRocketmqSampleApplication.class, args);
    }

}
