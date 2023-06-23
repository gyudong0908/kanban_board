package com.kanban.back.repository;

import com.kanban.back.entity.RequestTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTableRepository extends JpaRepository<RequestTable, Integer> {
}
