package com.example.jjapjun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/user/registerForm")
    public String registerForm() {
        return "user/registerForm";
    }

    @PostMapping("/user/register")
    public String register() {
        return "index";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}
