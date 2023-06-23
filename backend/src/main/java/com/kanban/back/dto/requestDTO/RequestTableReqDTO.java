package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.Card;
import com.kanban.back.entity.RequestTable;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RequestTableReqDTO {
    private Integer request_id;
    private LocalDateTime request_date;
    private String request_user;
    private Integer c_id;

    public RequestTable toEntity(){
        return RequestTable.builder()
                .request_id(request_id)
                .request_date(request_date)
                .request_user(request_user)
                .card(new CardReqDTO(){{setC_id(c_id);}}.toEntity())
                .build();
    }
}