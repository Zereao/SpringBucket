package com.zereao.eureka.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Darion Mograine H
 * @version 2019/02/19  11:33
 */
@RestController
public class TestController {
    @GetMapping("hi")
    public String test(@RequestParam("name") String param) {
        System.out.println("============== param = " + param + " ==============");
        return "SUCCESS";
    }
}
