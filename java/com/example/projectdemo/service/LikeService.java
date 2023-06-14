package com.example.projectdemo.service;

import com.example.projectdemo.dto.CommentDTO;
import com.example.projectdemo.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public Optional<LikeDTO> findByCmId(CommentDTO commentDTO) {

        return null;
    }
}
