package com.example.jjapjun.controller;

import com.example.jjapjun.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
public class BoardController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
