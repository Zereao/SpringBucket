package com.zereao.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZookeeperApplication.class, args);
    }

}
