package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardReqDTO {
    private String b_name;
    private String b_goal;
    private Integer b_id;
    private String b_creator;
    private String b_admin;

    public Board toEntity(){
        return Board.builder()
                .b_name(b_name)
                .b_goal(b_goal)
                .b_id(b_id)
                .b_creator(b_creator)
                .b_admin(b_admin)
                .build();
    }

}
