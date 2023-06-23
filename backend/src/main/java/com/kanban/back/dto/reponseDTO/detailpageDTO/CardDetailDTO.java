package com.kanban.back.dto.reponseDTO.detailpageDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDetailDTO {
    private Integer c_id;
    private String c_description;
    private LocalDate c_start_date;
    private LocalDate c_end_date;
    private Long d_day;
    private List<CardPartnerDetailDTO> cardPartners;
    private List<CommentDetailDTO> comments;
    private List<TagDetailDTO> tags;
    private List<FilesDetailDTO> files;
}
