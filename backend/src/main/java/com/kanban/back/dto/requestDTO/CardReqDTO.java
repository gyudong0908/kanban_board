package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardReqDTO {
    private String c_title;
    private Integer b_id;
    private Integer c_position;
    private String c_creator;
    private Integer c_id;
    private Integer t_id;
    private String c_upd_p;
    private String c_del_p;
    private String c_description;
    private Date c_start_date;
    private Date c_end_date;

    public Card toEntity(){
        return Card.builder()
                .c_title(c_title)
                .board(new BoardReqDTO(){{setB_id(b_id);}}.toEntity())
                .c_position(c_position)
                .c_creator(c_creator)
                .c_id(c_id)
                .task(new TaskReqDTO(){{setT_id(t_id);}}.toEntity())
                .c_upd_p(c_upd_p)
                .c_del_p(c_del_p)
                .c_description(c_description)
                .build();
    }
}
