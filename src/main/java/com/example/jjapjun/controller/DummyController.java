package com.example.jjapjun.controller;

import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.UserRepository;
import com.example.jjapjun.role.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DummyController {

    private final UserRepository userRepository;

    @Autowired
    public DummyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/dummy/register")
    public String register(User user) {
        log.warn("id={}", user.getId());
        log.warn("username={}", user.getUsername());
        log.warn("password={}", user.getPassword());
        log.warn("email={}", user.getEmail());

        user.setUserRole(UserRole.USER);
        userRepository.save(user);
        return "ok";
    }

    @GetMapping("/dummy/user/{id}")
    public User detail() {

    }
}
