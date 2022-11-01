package com.example.jjapjun.controller;

import com.example.jjapjun.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DummyController {

    @PostMapping("/dummy/register")
    public String register(User user) {
        log.warn("id={}", user.getId());
        log.warn("username={}", user.getUsername());
        log.warn("password={}", user.getPassword());
        log.warn("email={}", user.getEmail());

        return "ok";
    }
}
