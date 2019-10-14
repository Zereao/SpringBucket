package com.zereao.consul.producer1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProducerApp1 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerApp1.class, args);
    }

}
