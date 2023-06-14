package com.example.projectdemo.controller;


import com.example.projectdemo.dto.BoardDTO;
import com.example.projectdemo.dto.CommentDTO;
import com.example.projectdemo.service.BoardService;
import com.example.projectdemo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ForumController {
    private final BoardService boardService;
    private final CommentService commentService;


    @GetMapping("/forum") // /board/forum
    public String findAllForum(Model model) {
        //DB에서 전체 게시글 데이터를 가져와서 boardList.html에 보여준다.
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("forumList", boardDTOList);

        return "boardlist";
    }

    //상세 보기
    @GetMapping("/forum/{postNo}")
    public String findById(@PathVariable Long postNo, Model model, @PageableDefault(page=1)Pageable pageable){
        // 조회수 처리
        boardService.updateViews(postNo);
        //게시글의 데이터를 가져와서 boardview.html에 출력
        BoardDTO boardDTO = boardService.findById(postNo);
        //댓글 목록 가져오기
        List<CommentDTO> commentDTOList = commentService.findAll(postNo);
//        Page<CommentDTO> commentList =  commentService.paging(pageable);
//        int blockLimit = 3; // 보여지는 페이지 번호 갯수
//        int startPage =(((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//        int endPage = ((startPage + blockLimit - 1) < commentList.getTotalPages()) ? startPage + blockLimit - 1 : commentList.getTotalPages();


        model.addAttribute("forum", boardDTO);
        model.addAttribute("commentList", commentDTOList);

        return "boardview";
    }

    //글쓰기 양식 불러오기
    @GetMapping("/forum/write")
    public String saveForm(){
        return "boardwrite";
    }

    //글쓰기 Proc
    @PostMapping("/forum/write")
    public String save(@ModelAttribute BoardDTO boardDTO, Model model) throws IOException {
        System.out.println("boardDTO = " + boardDTO);
        Long postNo = boardService.save(boardDTO);
        model.addAttribute("forum", boardDTO);

        return "redirect:/board/forum/"+postNo;

    }

    @GetMapping("/forum/update/{postNo}")
    public String updateForm(@PathVariable Long postNo, Model model){
        BoardDTO boardDTO = boardService.findById(postNo);
        model.addAttribute("forumUpdate", boardDTO);

        System.out.println("GET update boardDTO = "+boardDTO);

        return "boardupdate";
    }

    @PostMapping("/forum/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) throws IOException {
        //ModelAttribute로 받아옴
        BoardDTO board = boardService.update(boardDTO);

        System.out.println("POST update boardDTO = "+boardDTO);
        List<CommentDTO> commentDTOList = commentService.findAll(board.getPostNo());

        model.addAttribute("forum", board);
        model.addAttribute("commentList",commentDTOList);

        return "boardview";
    }

    @GetMapping("/forum/delete/{postNo}")
    public String delete(@PathVariable Long postNo){
        boardService.delete(postNo);
        return "redirect:/board/forum";
    }

    @GetMapping("/forum/paging")
    public String paging(String searchKeyword,
                         @PageableDefault(page = 1) Pageable pageable,
                         Model model ) {
        log.info("진입 포인트");

//        Page<BoardDTO> boardList = boardService.paging(pageable);

        log.info("Search Keyword: " + searchKeyword);

//        Page<BoardDTO> boardList = boardService.forumSearch(searchKeyword, pageable);


        Page<BoardDTO> boardList;


        if (searchKeyword == null || searchKeyword.isBlank()) {
            boardList = boardService.paging(pageable);
        } else {
            boardList = boardService.forumSearch(searchKeyword, pageable);
        }

        int nowPage = boardList.getPageable().getPageNumber() + 1;
        int blockLimit = 3; // 보여지는 페이지 번호 갯수
        int startPage =(((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();

        model.addAttribute("forumList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("nowPage", nowPage);

        return "boardlist";
    }

//    @GetMapping("/forum/paging")
//    public String search(@RequestParam(required = false) String searchKeyword, @PageableDefault(page = 1) Pageable pageable,
//                         Model model) {
//        log.info("최조 진입");
//        Page<BoardDTO> boardList = boardService.forumSearch(searchKeyword, pageable);
//        log.info("진입확인");
//        log.info(String.valueOf(boardList.get()));
//        int blockLimit = 3; // 보여지는 페이지 번호 갯수
//        int nowPage = boardList.getPageable().getPageNumber() + 1;
//        int startPage =(((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
//        int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();
//
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("forumList", boardList);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "boardlist";
//
//    }

}
