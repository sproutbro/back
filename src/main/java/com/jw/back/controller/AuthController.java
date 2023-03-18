package com.jw.back.controller;

import com.jw.back.filter.JwtTokenProvider;
import com.jw.back.model.User;
import com.jw.back.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        // 사용자 인증 처리
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(userDetails.getUsername());

        // HTTP 응답에 JWT 토큰 추가하여 전송
        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .build();
    }
}
