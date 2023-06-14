package com.example.projectdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReCmDTO {

    private Long recmId; // 대댓글 번호(pk)
    private String recmContent; //대댓글 내용
    private LocalDateTime recmDate; // 대댓글 작성 시간
    private Long cmId; // 대댓글 달린 댓글 번호
}
