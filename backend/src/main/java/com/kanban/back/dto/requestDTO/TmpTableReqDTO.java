package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TmpTableReqDTO {
    private Integer c_id;
    private String u_id;
    private String commit_status;
    private Integer tmp_id;

    public TmpTable toEntity(){
        return TmpTable.builder()
                .card(new CardReqDTO(){{setC_id(c_id);}}.toEntity())
                .userTable(new UserTableReqDTO(){{setU_id(u_id);}}.toEntity())
                .commit_status(commit_status)
                .tmp_id(tmp_id)
                .build();
    }
}
