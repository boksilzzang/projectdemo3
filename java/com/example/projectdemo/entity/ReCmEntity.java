package com.example.projectdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name="recmTable")
public class ReCmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long recmId;

    @Column(nullable = false)
    private String recmContent;

    @Column
    private LocalDateTime recmDate;

    @PrePersist
    private void perPersist() {
        recmDate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cm_id")
    private CommentEntity commentEntity;

}
