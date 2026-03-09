package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
// JSON/テキストを直接返すことを宣言する
@RestController
public class HelloController {

    // HTTP GET /hello にマッピング
    @GetMapping("/")
    public String Welcome() {
        return "Welcome Home!";
    }
    
    // HTTP GET /hello にマッピング
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    // パスパラメータを受け取る例
    @GetMapping("/hello/{name}")
    public String helloName(@org.springframework.web.bind.annotation.PathVariable String name) {
        return "Hello, " + name + "!";
    }
}