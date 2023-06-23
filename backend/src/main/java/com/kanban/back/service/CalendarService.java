package com.kanban.back.service;

import com.kanban.back.Exception.NotFindData;
import com.kanban.back.dto.requestDTO.CalendarReqDTO;
import com.kanban.back.entity.Calendar;
import com.kanban.back.repository.CalendarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CalendarService {
    CalendarRepository calendarRepository;
    @Autowired
    CalendarService (CalendarRepository calendarRepository){ this.calendarRepository = calendarRepository; }

    public List<CalendarReqDTO> getCalendar(String month){
        return calendarRepository.getCalendarsByMonth(month).stream().map(s->s.toDTO()).toList();
    }
    @Transactional
    public void updateCalendar(CalendarReqDTO calendarReqDTO){
        Calendar calendar = calendarRepository.getById(calendarReqDTO.getCal_id());
        calendar.update(calendarReqDTO);

    }
    @Transactional
    public CalendarReqDTO restoreCalendar(Integer cal_id){
        calendarRepository.restoreCalendar(cal_id);
        return calendarRepository.getById(cal_id).toDTO();
    }
}
