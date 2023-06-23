package com.kanban.back.dto.requestDTO;

import com.kanban.back.entity.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CardPartnerReqDTO {
    private Integer b_id;
    private Integer c_id;
    private String u_id;
    private Integer partner_id;

    public CardPartner toEntity(){
        return CardPartner.builder()
                .board(new BoardReqDTO(){{setB_id(b_id);}}.toEntity())
                .card(new CardReqDTO(){{setC_id(c_id);}}.toEntity())
                .userTable(new UserTableReqDTO(){{setU_id(u_id);}}.toEntity())
                .partner_id(partner_id)
                .build();
    }

}
