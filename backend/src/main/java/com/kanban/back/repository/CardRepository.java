package com.kanban.back.repository;

import com.kanban.back.entity.Board;
import com.kanban.back.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card, Integer> {
    @Procedure(procedureName = "restore_card")
    void restoreCard(@Param(value = "c_id") Integer c_id);
}
