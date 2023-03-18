package com.jw.back.repository;

import com.jw.back.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    Integer save(Board board);

    Integer delete(Integer id);

    Board findById(Long id);

    List<Board> findAll();

    Integer count();
}
