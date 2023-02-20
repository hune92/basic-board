package com.toffee.nut.board.repository;

import com.toffee.nut.board.domain.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .loginId("kth9024")
                .loginPwd("bbbbb")
                .userName("김태훈")
                .regDtm(LocalDateTime.now())
                .updateDtm(LocalDateTime.now())
                .nickname("훈치치")
                .build();
    }

    @Test
    void 유저등록(){
        userRepository.save(user);

        assertTrue(userRepository.findByLoginId("kth9024").isPresent());
    }

}