package com.jw.back.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Board {
    private long id;
    private String author;
    private String title;
    private String content;
    private Timestamp created_at;
    private int views;
}
