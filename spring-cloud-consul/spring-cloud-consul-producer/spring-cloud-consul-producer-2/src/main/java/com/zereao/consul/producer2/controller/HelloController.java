package com.zereao.consul.producer2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Darion Mograine H
 * @version 2019/03/15  10:30
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello consul! This is consul producer 2";
    }
}
