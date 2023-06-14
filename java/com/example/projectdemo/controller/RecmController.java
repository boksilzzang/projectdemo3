package com.example.projectdemo.controller;

import com.example.projectdemo.dto.ReCmDTO;
import com.example.projectdemo.service.ReCmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recomment")
public class RecmController {
    private ReCmService recmService;

    @PostMapping("/write")
    public String save(@ModelAttribute ReCmDTO recmDTO){
        System.out.println("reCmDTO = " + recmDTO);
        recmService.save(recmDTO);

        return "대댓글 작성 성공";
    }
}
