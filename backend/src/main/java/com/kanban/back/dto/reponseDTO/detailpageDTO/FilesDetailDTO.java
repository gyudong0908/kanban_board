package com.kanban.back.dto.reponseDTO.detailpageDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FilesDetailDTO {
    private Integer file_id;
    private String file_original_name;
    private LocalDateTime file_save_date;
}
