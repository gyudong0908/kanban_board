package com.kanban.back.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanban.back.dto.reponseDTO.mainpageDTO.TaskMainDTO;
import com.kanban.back.dto.requestDTO.TaskReqDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="task")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer t_id;
    private String t_name;
    @CreatedDate
    private LocalDateTime t_create_date;
    private String t_type;
    @LastModifiedDate
    private LocalDateTime t_upd_date;
    private String t_creator;
    private String t_upd_p;
    private String t_del_p;
    private Integer t_position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "b_id", referencedColumnName = "b_id")
    })
    @JsonIgnore
    private Board board;

//    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    @OrderBy("c_position asc")
    private List<Card> cards;

    public TaskMainDTO toMainDTO(){
        return TaskMainDTO.builder()
                .t_id(t_id)
                .t_name(t_name)
                .t_create_date(t_create_date)
                .t_type(t_type)
                .t_upd_date(t_upd_date)
                .t_creator(t_creator)
                .t_upd_p(t_upd_p)
                .t_del_p(t_del_p)
                .t_position(t_position)
                .cards((Objects.nonNull(cards)) ? cards.stream().map(s-> s.toMainDTO()).toList() : Collections.emptyList())
                .build();
    }

    public void update(TaskReqDTO taskReqDTO){
        if (taskReqDTO.getT_name() != null) this.t_name = taskReqDTO.getT_name();
        if (taskReqDTO.getT_upd_p() != null) this.t_upd_p = taskReqDTO.getT_upd_p();
        if (taskReqDTO.getT_del_p() != null) this.t_del_p = taskReqDTO.getT_del_p();
        if (taskReqDTO.getT_position() != null) this.t_position = taskReqDTO.getT_position();
    }
}