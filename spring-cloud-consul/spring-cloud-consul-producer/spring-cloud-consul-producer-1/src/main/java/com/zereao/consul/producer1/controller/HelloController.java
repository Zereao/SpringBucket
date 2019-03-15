package com.zereao.consul.producer1.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Darion Mograine H
 * @version 2019/03/15  10:30
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @LoadBalanced
    @RequestMapping("/hello")
    public String hello() {
        return "hello consul! This is consul producer 1";
    }
}
