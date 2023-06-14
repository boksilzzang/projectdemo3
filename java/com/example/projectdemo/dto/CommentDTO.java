package com.example.projectdemo.dto;

import com.example.projectdemo.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long cmId; //댓글 번호 (pk)
    private String cmContent; //댓글내용
    private LocalDateTime cmDate; //댓글 작성시간
    private Long postNo; //댓글 달린 게시판 번호

//    public CommentDTO (Long cmId, String cmContent, LocalDateTime cmDate, Long postNo) {
//        this.cmId = cmId;
//        this.cmContent = cmContent;
//        this.cmDate = cmDate;
//        this.postNo = postNo;
//    }
    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long postNo) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCmId(commentEntity.getCmId());
        commentDTO.setCmContent(commentEntity.getCmContent());
        commentDTO.setCmDate(commentEntity.getCmDate());
        commentDTO.setPostNo(postNo);

        return commentDTO;
    }

    /*
    public CommentDTO(String cmContent, Long postNo) {
        this.cmContent = cmContent;
        this.postNo = postNo;
    }

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long postNo) {
        CommentDTO commentDTO = new CommentDTO(commentEntity.getCmContent(), postNo);

        commentDTO.setCmId(commentEntity.getCmId());
        commentDTO.setCmContent(commentEntity.getCmContent());

        return commentDTO;
    }
    */


}
