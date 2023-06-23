package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.Calendar;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CalendarReqDTO {
    private Integer cal_id;
    private String cal_content;
    private LocalDate cal_date;
    private String writer;

    public Calendar toEntity(){
        return Calendar.builder()
                .cal_id(cal_id)
                .cal_content(cal_content)
                .cal_date(cal_date)
                .writer(writer)
                .build();
    }
}