package com.example.jjapjun.controller;

import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.UserRepository;
import com.example.jjapjun.role.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

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
    public User detail(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("없는 유저 id:" + id);
        });

        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pageUser = userRepository.findAll(pageable);
        List<User> users = pageUser.getContent();
        return users;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User requestUser) {
        log.warn("id={}", id);
        log.warn("password={}", requestUser.getPassword());
        log.warn("email={}", requestUser.getEmail());
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        //userRepository.save(user);

        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제실패";
        }

        return "삭제완료";
    }
}
