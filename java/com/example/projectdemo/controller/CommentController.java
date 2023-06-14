package com.example.projectdemo.controller;

import com.example.projectdemo.dto.CommentDTO;
import com.example.projectdemo.service.CommentService;
import com.example.projectdemo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final LikeService likeService;
    @PostMapping("/write")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
        Long saveResult = commentService.save(commentDTO);
        if(saveResult != null) {
            // 작성 성공하면 댓글 목록을 가져와서 리턴
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getPostNo());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{cmId}")
    public ResponseEntity delete(@PathVariable Long cmId, @ModelAttribute CommentDTO commentDTO) {
        commentService.delete(cmId);

        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getPostNo());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping("/like/{cmId}")
    public String likeComment(@PathVariable Long cmId) {
        CommentDTO commentDTO = commentService.findById(cmId);

        if (commentDTO != null) {
            Optional<LikeDTO> existingLike = likeService.findByCmId(commentDTO);
            if (!existingLike.isPresent()) {
                Like like = new Like();
                like.setComment(comment);
                like.setUser(user);
                likeRepository.save(like);
                comment.getLikes().add(like);
                commentRepository.save(comment);
            }
        }

        return "redirect:/comments"; // 좋아요 처리 후, 댓글 목록 페이지로 리다이렉트
    }
}
