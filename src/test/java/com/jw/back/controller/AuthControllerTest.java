package com.jw.back.controller;

import com.jw.back.auth.AuthController;
import com.jw.back.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    @DisplayName("아이디 중복검사")
    void 아이디중복검사() {
        User user = new User();
        user.setUsername("dddd");
        ResponseEntity<?> responseEntity = authController.checkDuplicateId(user);
//        assertThat(responseEntity.getBody()).isEqualTo("사용 가능한 아이디 입니다.");
        assertThat(responseEntity.getBody()).isEqualTo("아이디가 이미 존재합니다.");
    }

    @Test
    @DisplayName("회원가입 검사")
    void 회원가입검사() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("dddd@dddddsji.jid");
        ResponseEntity<?> responseEntity = authController.registerUser(user);
//        assertThat(responseEntity.getBody()).isEqualTo("회원 가입이 완료되었습니다.");
        assertThat(responseEntity.getBody()).isEqualTo("아이디가 이미 존재합니다.");
    }

    @Test
    @DisplayName("로그인 검사")
    void 로그인검사() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        ResponseEntity<?> responseEntity = authController.authenticateUser(user);

        assertThat(user.getUsername()).isEqualTo(responseEntity.getBody());
        System.out.println("responseEntity = " + responseEntity.getHeaders());
    }

}