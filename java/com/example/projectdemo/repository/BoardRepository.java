package com.example.projectdemo.repository;

import com.example.projectdemo.dto.BoardDTO;
import com.example.projectdemo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


     /*
      조회수 처리
      update board_table set board_views = board_views+1 where post_no=?
      */
    @Modifying
    @Query(value="update BoardEntity  b set b.views=b.views+1 where b.postNo=:postNo")
    void updateViews(@Param("postNo") Long postNo);

    //검색 기능
    Page<BoardEntity> findByTitleContaining(String searchKeyword, Pageable pageable);
}
