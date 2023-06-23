package com.kanban.back.dto.reponseDTO.mainpageDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentMainDTO {
    private UserTableMainDTO userTable;
    private Integer comment_id;
    private LocalDateTime comment_date;
    private String comment_contents;


}
