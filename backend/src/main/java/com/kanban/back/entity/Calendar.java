package com.kanban.back.entity;


import com.kanban.back.dto.requestDTO.CalendarReqDTO;
import com.kanban.back.dto.requestDTO.CardReqDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="calendar")
@Getter
@Builder
@ToString
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cal_id;
    private String cal_content;
    private LocalDate cal_date;
    private String writer;
    public CalendarReqDTO toDTO(){
        return CalendarReqDTO.builder()
                .cal_id(cal_id)
                .cal_content(cal_content)
                .cal_date(cal_date)
                .writer(writer)
                .build();
    }
    public void update(CalendarReqDTO calendarReqDTO){
        if(calendarReqDTO.getCal_content() != null) this.cal_content = calendarReqDTO.getCal_content();
        if(calendarReqDTO.getCal_date() != null) this.cal_date = calendarReqDTO.getCal_date();
        if(calendarReqDTO.getWriter() != null) this.writer = calendarReqDTO.getWriter();
    }
}
