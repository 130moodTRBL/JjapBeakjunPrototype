package com.example.jjapjun.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JpaRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public JpaRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void saveTest() {

    }
}
