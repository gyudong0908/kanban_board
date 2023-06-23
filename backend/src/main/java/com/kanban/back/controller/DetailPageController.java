package com.kanban.back.controller;

import com.kanban.back.dto.reponseDTO.detailpageDTO.*;
import com.kanban.back.dto.requestDTO.CalendarReqDTO;
import com.kanban.back.dto.requestDTO.CardPartnerReqDTO;
import com.kanban.back.dto.requestDTO.RequestTableReqDTO;
import com.kanban.back.dto.requestDTO.TagReqDTO;
import com.kanban.back.service.DetailPageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DetailPageController {
    DetailPageService detailPageService;
    @Autowired
    DetailPageController(DetailPageService detailPageService){
        this.detailPageService = detailPageService;
    }

    @GetMapping("detail/{c_id}")
    public ResponseEntity<?> getCardDetail(@PathVariable Integer c_id){
        try {

            CardDetailDTO cardDetailDTO = detailPageService.getCardDetail(c_id);
            return ResponseEntity.ok(cardDetailDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Card를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }

    }
    @GetMapping("boardusers/{b_id}")
    public ResponseEntity<?> getBoardUsers(@PathVariable Integer b_id){
        try {

            List<UserTableDetailDTO> userTableDetailDTOS = detailPageService.getBoardUsers(b_id);
            return ResponseEntity.ok(userTableDetailDTOS);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }

    @PostMapping("cardmember")
    public ResponseEntity<?> createCardMember(@RequestBody List<CardPartnerReqDTO> cardPartnerReqDTOs){
        try {

            detailPageService.createCardMember(cardPartnerReqDTOs);
            return ResponseEntity.ok(null);

        } catch (DataIntegrityViolationException e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다: 중복된 User입니다");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @PostMapping("Tag")
    public ResponseEntity<?> createTag(@RequestBody List<TagReqDTO> tagReqDTOs){
        try {

            detailPageService.createTag(tagReqDTOs);
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @PostMapping("request")
    public ResponseEntity<?> request(@RequestBody RequestTableReqDTO requestTableReqDTO){
        try {

            detailPageService.requetst(requestTableReqDTO);
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }

}