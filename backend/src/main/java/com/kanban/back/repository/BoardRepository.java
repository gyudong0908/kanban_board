package com.kanban.back.repository;

import com.kanban.back.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Procedure()
    Board get_id_procedure(@Param(value = "user") String user);

    @Query("select new map(b.board.b_id as b_id, b.board.b_name as b_name) from BoardUser b where b.userTable.u_id = :u_id")
    List<Map<String, Object>> getBoardName(@Param("u_id") String u_id);
}
