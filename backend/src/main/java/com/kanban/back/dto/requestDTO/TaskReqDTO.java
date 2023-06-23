package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskReqDTO {
    private Integer t_id;
    private String t_name;
    private String t_type;
    private String t_creator;
    private String t_upd_p;
    private String t_del_p;
    private Integer t_position;
    private Integer b_id;

    public Task toEntity(){
        return Task.builder()
                .t_id(t_id)
                .t_name(t_name)
                .t_type(t_type)
                .t_creator(t_creator)
                .t_upd_p(t_upd_p)
                .t_del_p(t_del_p)
                .t_position(t_position)
                .board(new BoardReqDTO(){{setB_id(b_id);}}.toEntity())
                .build();
    }
}
