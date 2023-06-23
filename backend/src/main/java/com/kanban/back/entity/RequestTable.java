package com.kanban.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="request_table")
@Getter
@Builder
@ToString
@EntityListeners(AuditingEntityListener.class)
public class RequestTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer request_id;
    @CreatedDate
    private LocalDateTime request_date;
    private String request_user;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    @JsonIgnore
    private Card card;
}