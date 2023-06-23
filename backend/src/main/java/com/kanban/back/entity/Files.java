package com.kanban.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanban.back.dto.reponseDTO.detailpageDTO.FilesDetailDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="files")
@Getter
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer file_id;
    @CreatedDate
    private LocalDateTime file_save_date;
    private String file_name;
    private String file_path;
    private String file_ext;
    private String file_original_name;
    private Long file_size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id", referencedColumnName = "c_id")
    @JsonIgnore
    private Card card;

    public FilesDetailDTO toDetailDTO(){
        return FilesDetailDTO.builder()
                .file_id(file_id)
                .file_original_name(file_original_name)
                .file_save_date(file_save_date)
                .build();
    }
}
