package com.zereao.rockectmq.starter.controller;

import com.zereao.rockectmq.starter.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zereao
 * @version 2019/04/08 16:49
 */
@RestController
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {this.testService = testService;}

    @GetMapping("test")
    public void send() {
        testService.test();
    }
}
