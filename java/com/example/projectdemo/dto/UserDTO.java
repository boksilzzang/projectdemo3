package com.example.projectdemo.dto;

import lombok.*;

import java.sql.Blob;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userNo;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private Long point;
    private Blob userPic;
    private String userInfo;
}
