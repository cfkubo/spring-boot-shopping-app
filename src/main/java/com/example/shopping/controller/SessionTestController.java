package com.example.shopping.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/testcart")
public class SessionTestController {
    private final TestCartService testCartService;

    public SessionTestController(TestCartService testCartService) {
        this.testCartService = testCartService;
    }

    @PostMapping("/add")
    public String add(@RequestParam int value) {
        testCartService.getList().add(value);
        return "added";
    }

    @GetMapping
    public List<Integer> get() {
        return testCartService.getList();
    }
}

@Service
@SessionScope
class TestCartService {
    private final List<Integer> list = new ArrayList<>();
    public List<Integer> getList() { return list; }
}