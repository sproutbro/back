package com.jw.back.service;

import com.jw.back.model.Test;
import com.jw.back.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @Override
    public List<Test> findAll() {

        return testRepository.findAll();
    }
}
