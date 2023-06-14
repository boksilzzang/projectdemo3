package com.example.projectdemo.repository;

import com.example.projectdemo.entity.CommentEntity;
import com.example.projectdemo.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    LikeEntity findByCmId(CommentEntity commentEntity);
}
