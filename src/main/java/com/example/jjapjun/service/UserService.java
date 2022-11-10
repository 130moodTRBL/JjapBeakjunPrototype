package com.example.jjapjun.service;

import com.example.jjapjun.entity.MySession;
import com.example.jjapjun.entity.User;
import com.example.jjapjun.repository.UserRepository;
import com.example.jjapjun.role.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(User user) {
        if(!duplicateRegister(user)) return null;
        user.setUserRole(UserRole.USER);
        userRepository.save(user);
        return user;
    }

    public boolean duplicateRegister(User user) {
        if(user.getUsername() == "" || user.getUsername() == " ") return false;

        int charCount = 0; int numCount = 0;
        String userPassword = user.getPassword();
        if(userPassword == " " || userPassword == "" || userPassword.length() < 5) return false;
        for (int i = 0; i < userPassword.length(); i++) {
            if(userPassword.charAt(i) >= '0' && userPassword.charAt(i) <= '9') numCount++;
            else charCount++;
        }
        if(charCount <= 0 || numCount <= 0) return false;
        return true;
    }

    @Transactional(readOnly = true)
    public User login(User user) {
        User principal = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        MySession.status = true;
        MySession.user = principal;
        return principal;
    }
}
