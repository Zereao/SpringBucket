package com.zereao.zookeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zereao
 * @version 2019/04/02 10:23
 */
@RestController
public class TestController {
    private final DiscoveryClient discoveryClient;

    @Autowired
    public TestController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    @GetMapping("/test")
    public String test() {
        //获取实例化的注册节点
        List<ServiceInstance> list = discoveryClient.getInstances("STORES");

        //获取实例化的服务
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            sb.append(list.get(0).getUri()).append(",");
        }
        return "hello world  " + sb.toString();
    }
}
