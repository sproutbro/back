package com.jw.back.auth;

import com.jw.back.auth.filter.JwtTokenProvider;
import com.jw.back.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService, AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (authRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디가 이미 존재합니다.");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            authRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입이 완료되었습니다.");
        }
    }

    @PostMapping("/check-duplicate-id")
    public ResponseEntity<?> checkDuplicateId(@RequestBody User user) {
        Optional<User> byUsername = authRepository.findByUsername(user.getUsername());
        if (byUsername.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("아이디가 이미 존재합니다.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 아이디 입니다.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        // 사용자 인증 처리
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        // JWT 토큰 생성
        String token = jwtTokenProvider.createToken(userDetails.getUsername());

        // HTTP 응답에 JWT 토큰 추가하여 전송
        return ResponseEntity.status(HttpStatus.OK)
                .header("Authorization", "Bearer " + token)
                .body(user.getUsername());
    }

}
