package com.zereao.annotationspel.controller;

import com.zereao.annotationspel.service.BusinessCacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author Zereao
 * @version 2019/10/14 11:29
 */
@RestController
@RequestMapping("spel")
public class AnnotationSpelController {
    @Resource
    public BusinessCacheService businessCacheService;

    @Resource

    @GetMapping
    public ResponseEntity<String> testSpel() {
        Method method = new Object() {}.getClass().getEnclosingMethod();
        businessCacheService.flushCache(method);
        return ResponseEntity.ok("SUCCESS");
    }
}
