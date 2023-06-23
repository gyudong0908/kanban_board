package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardUserReqDTO {
    private Integer b_id;
    private String  u_id;
    private Integer board_user_id;

    public BoardUser toEntity(){
        return BoardUser.builder()
                .board(new BoardReqDTO(){{setB_id(b_id);}}.toEntity())
                .userTable(new UserTableReqDTO(){{setU_id(u_id);}}.toEntity())
                .board_user_id(board_user_id)
                .build();
    }
}
