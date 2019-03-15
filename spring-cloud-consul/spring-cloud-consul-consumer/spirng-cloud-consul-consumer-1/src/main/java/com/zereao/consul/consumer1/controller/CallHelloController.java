package com.zereao.consul.consumer1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Darion Mograine H
 * @version 2019/03/15  13:29
 */
@RestController
public class CallHelloController {
    private final RestTemplate restTemplate;

    @Autowired
    public CallHelloController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @RequestMapping("/call")
    public String call() {
//        ServiceInstance serviceInstance = loadBalancer.choose("service-producer");
//        System.out.println("服务地址：" + serviceInstance.getUri());
//        System.out.println("服务名称：" + serviceInstance.getServiceId());

        String callServiceResult = restTemplate.getForObject("http://", String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }
}
