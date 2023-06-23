package com.kanban.back.dto.reponseDTO.mainpageDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskMainDTO {
    private Integer t_id;
    private String t_name;
    private LocalDateTime t_create_date;
    private String t_type;
    private LocalDateTime t_upd_date;
    private String t_creator;
    private String t_upd_p;
    private String t_del_p;
    private Integer t_position;
    private List<CardMainDTO> cards;
}
