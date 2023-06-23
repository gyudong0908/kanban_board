package com.kanban.back.repository;

import com.kanban.back.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardUserRepository extends JpaRepository<BoardUser, Integer> {
    @Query("select b from BoardUser b where b.board.b_id = :b_id")
    List<BoardUser> getBoardUsersByB_id(@Param("b_id") Integer b_id);
}
