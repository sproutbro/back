package com.jw.back.controller;

import com.jw.back.model.User;
import com.jw.back.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.existByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디가 이미 존재합니다.");
        } else {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료되었습니다.");
        }
    }

    @PostMapping("/check-duplicate-id")
    public ResponseEntity<?> checkDuplicateId(@RequestBody User user) {
        if (userService.existByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.OK).body("아이디가 이미 존재합니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 아이디 입니다.");
        }
    }

}
