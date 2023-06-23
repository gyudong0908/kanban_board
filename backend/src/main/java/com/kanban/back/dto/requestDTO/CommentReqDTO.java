package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentReqDTO {
    private Integer c_id;
    private String u_id;
    private Integer comment_id;
    private String comment_contents;

    public Comment toEntity(){
        return Comment.builder()
                .card(new CardReqDTO(){{setC_id(c_id);}}.toEntity())
                .userTable(new UserTableReqDTO(){{setU_id(u_id);}}.toEntity())
                .comment_id(comment_id)
                .comment_contents(comment_contents)
                .build();
    }

}
