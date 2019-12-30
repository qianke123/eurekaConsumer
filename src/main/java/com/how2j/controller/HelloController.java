package com.how2j.controller;

import com.how2j.pojo.User;
import com.how2j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public void test() {
        List<User> list = userService.getUsers();
        for (User user : list) {
            System.out.printf(String.valueOf(user));
        }
        System.out.println("success============");
    }
}
