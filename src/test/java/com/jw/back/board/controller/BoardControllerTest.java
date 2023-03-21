package com.jw.back.board.controller;

import com.jw.back.model.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardController boardController;

    @Test
    void getBoards() {
        List<Board> boards = boardController.findAll();
        System.out.println("boards = " + boards);
    }

    @Test
    void createBoard() {
        Board board = new Board();
        board.setAuthor("test");
        board.setTitle("dddd");
        board.setContent("djiosjfdio");
    }

    @Test
    void getPostById() {
        Board board = boardController.findById(1L);
        String author = board.getAuthor();
        assertThat(author).isEqualTo("test");
    }
}