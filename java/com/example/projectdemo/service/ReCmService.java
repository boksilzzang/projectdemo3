package com.example.projectdemo.service;

import com.example.projectdemo.dto.ReCmDTO;
import com.example.projectdemo.entity.CommentEntity;
import com.example.projectdemo.repository.CommentRepository;
import com.example.projectdemo.repository.ReCmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReCmService {
    private final ReCmRepository recmRepository;
    private final CommentRepository commentRepository;

    public void save(ReCmDTO recmDTO) {
        commentRepository.findById(recmDTO.getCmId());

    }
}
