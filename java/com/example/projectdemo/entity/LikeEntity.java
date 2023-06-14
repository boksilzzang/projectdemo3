package com.example.projectdemo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="liketable")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Column
    private Long cmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cmId")
    private CommentEntity commentEntity;
}
