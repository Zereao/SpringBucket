package com.zereao.demo.controller;

import com.zereao.demo.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Zereao
 * @version 2019/04/16 11:33
 */
@RestController
@RequestMapping("test")
@SessionAttributes({"user"})
public class ModelAttributeController {

    @GetMapping("user")
    public String localSessionAttributes(@RequestParam("id") Integer id, @RequestParam("name") String name, HttpSession session) {
        session.setAttribute("user", new User(id, name));
        return "SUCCESS";
    }

    @RequestMapping("get")
    public String sessionAttributes(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user.toString());
        return "SUCCESS";
    }

    @GetMapping("model")
    public String modelAttr(@ModelAttribute User user) {
        System.out.println(user.toString());
        return "SUCCESS";
    }

    @GetMapping("anno")
    public String anno(@ModelAttribute("user") User user, HttpSession session, HttpServletRequest request) {
        if (user.getId().equals(1)) {
            System.out.printf("request.getSession.getAttribute = %s", request.getSession().getAttribute("user").toString());
            System.out.printf("HttpSession.getAttribute = %s", session.getAttribute("user").toString());
            return "falied";
        }
        System.out.printf("---- request.getSession.getAttribute = %s", request.getSession().getAttribute("user").toString());
        System.out.printf("---- HttpSession.getAttribute = %s", session.getAttribute("user").toString());
        return "success";
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User(2, "小四");
    }
}
