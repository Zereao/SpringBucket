package com.zereao.project.controller;

import com.zereao.project.entity.User;
import com.zereao.project.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zereao
 * @version 2019/12/13 14:08
 */
@RestController
public class TestController {
    @Resource
    private UserService userService;

    @GetMapping("test")
    public ResponseEntity<Boolean> test() {
        List<Integer> idList = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        List<User> userList = userService.getUserByIdAndRole(idList, "dev");
        List<User> userListWithOutRole = userService.getUserByIdAndRole(idList, null);
        System.out.println(userList);
        System.out.println(userListWithOutRole);
        return ResponseEntity.ok(userList == userListWithOutRole);
    }
}
