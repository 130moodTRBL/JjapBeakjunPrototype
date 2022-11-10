package com.example.jjapjun.controller;

import com.example.jjapjun.entity.MySession;
import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.UserRepository;
import com.example.jjapjun.role.UserRole;
import com.example.jjapjun.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/user/registerForm")
    public String registerForm() {
        return "user/registerForm";
    }

    @PostMapping("/user/register")
    public String register(User user) {
        if(userService.register(user) == null) return "redirect:/user/registerForm";
        return "redirect:/";
    }

    @PutMapping("/user/update/{id}")
    public String update(@PathVariable Long id, User requestUser) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정 실패");
        });
        user.setUsername(requestUser.getUsername());
        user.setEmail(requestUser.getEmail());
        return "redirect:/";
    }

    @DeleteMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
           return new IllegalArgumentException("삭제 실패");
        });
        userRepository.delete(user);
        return "redirect:/";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/user/login")
    public String login(User user) {
        userService.login(user);
        return "redirect:/";
    }

    @GetMapping("/user/logout")
    public String logout() {
        MySession.user = null;
        MySession.status = false;
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("비정상적인 접근");
        });
        model.addAttribute("user", user);
        return "user/detail";
    }
}
