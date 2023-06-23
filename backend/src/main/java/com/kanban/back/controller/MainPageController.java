package com.kanban.back.controller;

import com.kanban.back.dto.reponseDTO.mainpageDTO.BoardMainDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.CardMainDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.TaskMainDTO;
import com.kanban.back.dto.requestDTO.BoardReqDTO;
import com.kanban.back.dto.requestDTO.CardReqDTO;
import com.kanban.back.dto.requestDTO.TaskReqDTO;
import com.kanban.back.entity.Card;
import com.kanban.back.service.MainPageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
public class MainPageController {
    MainPageService mainPageService;
    @Autowired
    MainPageController(MainPageService mainPageService){ this.mainPageService = mainPageService;}
    @PostMapping("board")
    public ResponseEntity<?> createBoard(@RequestBody BoardReqDTO boardReqDTO){
        try {

            BoardMainDTO boardMainDTO = mainPageService.createBoard(boardReqDTO);
            return ResponseEntity.ok(boardMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Board를 찾을 수 없습니다");
        }
        catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @GetMapping("board/{u_id}")
    public ResponseEntity<?> getBoard(@PathVariable String u_id){
        try {

            BoardMainDTO boardMainDTO = mainPageService.getBoard(u_id);
            return ResponseEntity.ok(boardMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Board를 찾을 수 없습니다");
        }
        catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @GetMapping("boardid/{b_id}")
    public ResponseEntity<?> getBoardB_id(@PathVariable Integer b_id){
        try {

            BoardMainDTO boardMainDTO = mainPageService.getBoardB_id(b_id);
            return ResponseEntity.ok(boardMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Board를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @PutMapping("board")
    public ResponseEntity<?> updateBoard(@RequestBody BoardReqDTO boardReqDTO){
        try {

            BoardMainDTO boardMainDTO = mainPageService.updateBoard(boardReqDTO);
            return ResponseEntity.ok(boardMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Board를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
    @DeleteMapping("board/{b_id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Integer b_id) {
        try {

            mainPageService.deleteBoard(b_id);
            return ResponseEntity.ok(null);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Board를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }

    @PostMapping("task")
    public ResponseEntity<?> createTask(@RequestBody TaskReqDTO taskReqDTO){
        try {

            TaskMainDTO taskMainDTO = mainPageService.createTask(taskReqDTO);
            return ResponseEntity.ok(taskMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Task를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }
    @PutMapping("task")
    public ResponseEntity<?> updateTask(@RequestBody TaskReqDTO taskReqDTO){
        try {

            TaskMainDTO taskMainDTO = mainPageService.updateTask(taskReqDTO);
            return ResponseEntity.ok(taskMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Task를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }
    @DeleteMapping("task/{t_id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer t_id){
        try {

             mainPageService.deleteTask(t_id);
            return ResponseEntity.ok(null);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Task를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }

    @PostMapping("card")
    public ResponseEntity<?> createCard(@RequestBody CardReqDTO cardReqDTO){
        try {

            CardMainDTO cardMainDTO = mainPageService.createCard(cardReqDTO);
            return ResponseEntity.ok(cardMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Card를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }
    @PutMapping("card")
    public ResponseEntity<?> updateCard(@RequestBody CardReqDTO cardReqDTO){
        try {

            CardMainDTO cardMainDTO = mainPageService.updateCard(cardReqDTO);
            return ResponseEntity.ok(cardMainDTO);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Card를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }
    @DeleteMapping("card/{c_id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer c_id){
        try {
             mainPageService.deleteCard(c_id);
            return ResponseEntity.ok(null);

        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("해당 Card를 찾을 수 없습니다");
        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }    }
    @GetMapping("restore/card/{c_id}")
    public ResponseEntity<?> restoreCard(@PathVariable Integer c_id){
        try {

            CardMainDTO cardMainDTO = mainPageService.restoreCard(c_id);
            return ResponseEntity.ok(cardMainDTO);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }

}