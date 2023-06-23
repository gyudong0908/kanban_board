package com.kanban.back.dto.reponseDTO.mainpageDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardMainDTO {
    private String b_name;
    private String b_goal;
    private Integer b_id;
    private LocalDateTime b_create_date;
    private LocalDateTime b_upd_date;
    private String b_creator;
    private String b_admin;
//    @JsonIgnoreProperties(value = {"t_create_date","t_type","t_upd_date","t_upd_p","t_del_p","t_del_yn"})
    private List<TaskMainDTO> tasks;
    private List<CardPartnerMainDTO> cardPartners;
    private List<UserTableMainDTO> userTables;
    private List<Map<String,Object>> boradnames;
}
