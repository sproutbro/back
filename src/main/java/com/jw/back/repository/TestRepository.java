package com.jw.back.repository;

import com.jw.back.model.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestRepository {
    List<Test> findAll();
}
