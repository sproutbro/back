package com.jw.back.controller;

import com.jw.back.model.Test;
import com.jw.back.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/1")
    public String test1() {
        return "Hello world!";
    }

    @GetMapping("/2")
    public List<Test> test2() {
        List<Test> all = testService.findAll();
        return testService.findAll();
    }

}
