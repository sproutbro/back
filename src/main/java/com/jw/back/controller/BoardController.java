package com.jw.back.controller;

import com.jw.back.model.Board;
import com.jw.back.repository.BoardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    private final BoardRepository boardRepository;

    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @PostMapping
    public Integer createBoard(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @GetMapping("/{id}")
    public Board getPostById(@PathVariable Long id) {
        return boardRepository.findById(id);
    }
}
