package com.zereao.consul.producer2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsulProducerApp2 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerApp2.class, args);
    }

}
