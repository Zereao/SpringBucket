package com.zereao.consul.consumer1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Darion Mograine H
 * @version 2019/03/15  11:53
 */
@RestController
@RequestMapping("test")
public class ServiceController {
    private final LoadBalancerClient loadBalancer;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public ServiceController(LoadBalancerClient loadBalancer, DiscoveryClient discoveryClient) {
        this.loadBalancer = loadBalancer;
        this.discoveryClient = discoveryClient;
    }

    /**
     * 获取所有服务
     */
    @RequestMapping("/services")
    public Object services() {
        List<ServiceInstance> list = discoveryClient.getInstances("service-producer");
        System.out.println(list);
        return list;
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/discover")
    public Object discover() {
        String result = loadBalancer.choose("service-producer").getUri().toString();
        System.out.println(result);
        return result;
    }
}
