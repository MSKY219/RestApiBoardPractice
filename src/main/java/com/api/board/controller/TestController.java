package com.api.board.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/test")
    public String getTest () {
        return "GET으로 요청을 받음";
    }

    @PostMapping("/test")
    public String postTest () {
        return "POST로 요청을 받음";
    }

    @PatchMapping("/test")
    public String patchTest () {
        return "PATCH로 요청을 받음";
    }

    @DeleteMapping("/test")
    public String deleteTest () {
        return "DELETE로 요청을 받음";
    }
}
