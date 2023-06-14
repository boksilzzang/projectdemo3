package com.example.projectdemo.repository;

import com.example.projectdemo.entity.BoardEntity;
import com.example.projectdemo.entity.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    //댓글 조회 기능 => boardEntity(postNo)를 기준으로 cmId 내림차순으로 댓글 findAll
    List<CommentEntity> findAllByBoardEntityOrderByCmIdDesc(BoardEntity boardEntity);


}
