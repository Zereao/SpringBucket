package com.zereao.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zereao
 * @version 2019/05/16 16:28
 */
@RestController
@RequestMapping("test")
public class SecurityController {

    @GetMapping("sec")
    public ResponseEntity<String> testSec() {
        return ResponseEntity.ok("测试是否被拦截");
    }
}
