package com.zereao.multidatasource.mybatis.controller;

import com.zereao.multidatasource.mybatis.entity.student.Student;
import com.zereao.multidatasource.mybatis.service.UserStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Zereao
 * @version 2019/12/13 11:44
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private UserStudentService userStudentService;

    @GetMapping("getStudentByUserId")
    public ResponseEntity<Student> getStudentByUserId(@RequestParam("userId") Integer userId) {
        Student student = userStudentService.getStudentByUserId(userId);
        return ResponseEntity.ok(student);
    }
}
