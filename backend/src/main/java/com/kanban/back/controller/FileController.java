package com.kanban.back.controller;

import com.kanban.back.dto.reponseDTO.detailpageDTO.FilesDetailDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.BoardMainDTO;
import com.kanban.back.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class FileController {
    FileService fileService;
    @Autowired
    FileController(FileService fileService){this.fileService = fileService;}

    // 하나의 파일만 보낼 때 사용
    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("c_id") Integer c_id) {
        try {

            fileService.storeFile(file, c_id);
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }


    }

    // 여러개의 파일을 보낼 때 사용
    @PostMapping("/uploadmultiplefiles")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("c_id") Integer c_id) {
        try {

            for (MultipartFile file : files){
                fileService.storeFile(file,c_id);
            }
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }

    }

    @GetMapping("/downloadfile/{file_id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer file_id, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(file_id);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다: 해당 파일을 찾을 수 없습니다");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
    @DeleteMapping("deletefile/{file_id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer file_id){
        try {
            fileService.deleteFile(file_id);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @GetMapping("restore/file/{file_id}")
    public ResponseEntity<?> restoreFile(@PathVariable Integer file_id){
        try{
            FilesDetailDTO filesDetailDTO = fileService.restoreFile(file_id);
            return ResponseEntity.ok(filesDetailDTO);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 카드을 찾을 수 없습니다");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void hardDeleteFile() {
        try {
            fileService.hardDeleteFile();
        }catch (IOException e){
            System.out.println("파일 삭제에 실패했습니다.");
            e.printStackTrace();
        }
    }
}