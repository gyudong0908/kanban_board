package com.kanban.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kanban.back.dto.reponseDTO.detailpageDTO.CardDetailDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.CardMainDTO;
import com.kanban.back.dto.requestDTO.CardReqDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "card")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Card {
    private String c_title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "b_id", referencedColumnName = "b_id")
//            @JoinColumn(name = "b_admin", referencedColumnName = "b_admin")
    })
    @JsonIgnore
    private Board board;

    private Integer c_position;
    @CreatedDate
    private LocalDateTime c_create_date;
    private String c_creator;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer c_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "t_id", referencedColumnName = "t_id")
    @JsonIgnore
    private Task task;

    private String c_upd_p;
    private String c_del_p;
    @LastModifiedDate
    private LocalDateTime c_upd_date;
    private String c_description;

    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<CardPartner> cardPartners;
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Tag> tags;
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<TmpTable> tmpTables;
    @OneToMany(mappedBy = "card", cascade = CascadeType.REMOVE)
    private List<Files> files;
    @OneToOne(mappedBy = "card",cascade = CascadeType.REMOVE)
    private RequestTable requestTable;
    private LocalDate c_start_date;
    private LocalDate c_end_date;

    public CardMainDTO toMainDTO(){
        return CardMainDTO.builder()
                .c_title(c_title)
                .c_position(c_position)
                .c_create_date(c_create_date)
                .c_creator(c_creator)
                .c_id(c_id)
                .c_upd_p(c_upd_p)
                .c_del_p(c_del_p)
                .c_upd_date(c_upd_date)
                .c_description(c_description)
                .cardPartners((Objects.nonNull(cardPartners)) ? cardPartners.stream().map(s-> s.toMainDTO()).toList() : Collections.emptyList())
                .comments((Objects.nonNull(comments)) ? comments.stream().map(s-> s.toMainDTO()).toList() : Collections.emptyList())
                .tags((Objects.nonNull(tags)) ? tags.stream().map(s-> s.toMainDTO()).toList() : Collections.emptyList())
                .tmpTables((Objects.nonNull(tmpTables)) ? tmpTables.stream().map(s-> s.toMainDTO()).toList() : Collections.emptyList())
                .build();
    }
    public CardDetailDTO toDetailDTO(){
        return CardDetailDTO.builder()
                .c_id(c_id)
                .c_description(c_description)
                .cardPartners((Objects.nonNull(cardPartners)) ? cardPartners.stream().map(s-> s.toDetailDTO()).toList() : Collections.emptyList())
                .comments((Objects.nonNull(comments)) ? comments.stream().map(s-> s.toDetailDTO()).toList() : Collections.emptyList())
                .tags((Objects.nonNull(tags)) ? tags.stream().map(s-> s.toDetailDTO()).toList() : Collections.emptyList())
                .c_start_date(c_start_date)
                .c_end_date(c_end_date)
                .files((Objects.nonNull(files)) ? files.stream().map(s-> s.toDetailDTO()).toList() : Collections.emptyList())
                .build();
    }

    public void update(CardReqDTO cardReqDTO){
        if(cardReqDTO.getC_title() != null) this.c_title = cardReqDTO.getC_title();
        if(cardReqDTO.getC_position() != null) this.c_position = cardReqDTO.getC_position();
        if(cardReqDTO.getC_upd_p() != null) this.c_upd_p = cardReqDTO.getC_upd_p();
        if(cardReqDTO.getC_del_p() != null) this.c_del_p = cardReqDTO.getC_del_p();
        if(cardReqDTO.getC_description() != null) this.c_description = cardReqDTO.getC_description();
    }


}
