package com.example.projectdemo.service;

import com.example.projectdemo.dto.BoardDTO;
import com.example.projectdemo.dto.CommentDTO;
import com.example.projectdemo.entity.BoardEntity;
import com.example.projectdemo.entity.CommentEntity;
import com.example.projectdemo.repository.BoardRepository;
import com.example.projectdemo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    //댓글 쓰기
    public Long save(CommentDTO commentDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getPostNo());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);
            return commentRepository.save(commentEntity).getCmId();
        } else {
            return null;
        }
    }

    //댓글 조회
    public List<CommentDTO> findAll(Long postNo) {
        BoardEntity boardEntity = boardRepository.findById(postNo).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByCmIdDesc(boardEntity);

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for(CommentEntity commentEntity : commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, postNo);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }


    //댓글 삭제
    public void delete(Long cmId) {
        commentRepository.deleteById(cmId);
    }

    public CommentDTO findById(Long cmId) {
        commentRepository.findById(cmId);

        return null;
    }
}
