package com.jw.back.repository;

import com.jw.back.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    void insertUser(User user);
}
