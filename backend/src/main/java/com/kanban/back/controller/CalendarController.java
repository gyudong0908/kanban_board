package com.kanban.back.controller;

import com.kanban.back.dto.reponseDTO.mainpageDTO.CardMainDTO;
import com.kanban.back.dto.requestDTO.CalendarReqDTO;
import com.kanban.back.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {
    CalendarService calendarService;
    @Autowired
    CalendarController(CalendarService calendarService){this.calendarService = calendarService;}

    @GetMapping("calendar/{month}")
    public ResponseEntity<?> getCalendar(@PathVariable String month){
        try {

            List<CalendarReqDTO> calendarReqDTOS = calendarService.getCalendar(month);
            return ResponseEntity.ok(calendarReqDTOS);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }

    @PutMapping("calendar")
    public ResponseEntity<?> updateCalendar(@RequestBody CalendarReqDTO calendarReqDTO){
        try {

            calendarService.updateCalendar(calendarReqDTO);
            return ResponseEntity.ok(null);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }

    }

    @GetMapping("resotre/calendar/{cal_id}")
    public ResponseEntity<?> restoreCalendar(@PathVariable Integer cal_id){
        try {

            CalendarReqDTO calendarReqDTO = calendarService.restoreCalendar(cal_id);
            return ResponseEntity.ok(calendarReqDTO);

        } catch (Exception e) {
            // 오류 발생 시 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("오류가 발생했습니다\n [" + e.getClass().getSimpleName()+ "]: "+ e.getMessage());
        }
    }
}

