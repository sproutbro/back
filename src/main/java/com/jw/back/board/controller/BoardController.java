package com.jw.back.board.controller;

import com.jw.back.model.Board;
import com.jw.back.board.repository.BoardRepository;
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
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @PostMapping
    public Board save(@RequestBody Board board) {
        return boardRepository.save(board);
    }

    @GetMapping("/{id}")
    public Board findById(@PathVariable Long id) {
        return boardRepository.findById(id).get();
    }

}
