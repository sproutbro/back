package com.jw.back.service;

import com.jw.back.model.User;
import com.jw.back.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.insertUser(user);
    }

    public Boolean existByUsername(String username) {
        Boolean result = false;
        User findUser = userRepository.getUserByUsername(username);
        if (findUser != null) {
            result = true;
        }
        return result;
    }
}
