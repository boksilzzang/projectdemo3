package com.example.projectdemo.entity;

import com.example.projectdemo.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="commentTable")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmId;

    @Column(nullable=false)
    private String cmContent;

    @Column
    private LocalDateTime cmDate;

//    @Column
//    private boolean like;

    @PrePersist
    private void perPersist() {
        cmDate = LocalDateTime.now();
    }

    // board : comment = 1 : N (1 개의 게시글에 여러 댓글 달 수 있다)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_no")
    private BoardEntity boardEntity;

    @OneToMany(mappedBy = "commentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReCmEntity> reCmEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "commentEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> likeEntity = new ArrayList<>();

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setCmContent(commentDTO.getCmContent());
        commentEntity.setBoardEntity(boardEntity);

        return commentEntity;
    }


}
