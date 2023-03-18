package com.jw.back.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private String roles;
}
