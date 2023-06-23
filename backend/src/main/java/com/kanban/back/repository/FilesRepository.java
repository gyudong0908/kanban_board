package com.kanban.back.repository;

import com.kanban.back.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Integer> {
    @Procedure(procedureName = "restore_files")
    void restoreFile(@Param(value = "file_id") Integer file_id);

    @Query(value = "delete from deletedFilesTBL where deletedDate < DATE_SUB(NOW(), INTERVAL 30 DAY)",nativeQuery = true)
    void hardDeleteFileTable();
    @Query(value = "select * from deletedFilesTBL where deletedDate < DATE_SUB(NOW(), INTERVAL 30 DAY)",nativeQuery = true)
    List<Files> hardDeleteFile();

}
