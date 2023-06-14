package com.example.projectdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "main";
    }

    @ResponseBody // 타임리프를 못찾았다는 이유로 추가한 어노테이션 : 추가하니까 실행 됨
    @GetMapping("/api/hello")
    public String test() {
        return "ㅂㅂㅂㅂ2";
    }
}
