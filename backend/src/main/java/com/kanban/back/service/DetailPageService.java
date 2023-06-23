package com.kanban.back.service;

import com.kanban.back.Exception.NotFindData;
import com.kanban.back.dto.reponseDTO.detailpageDTO.*;
import com.kanban.back.dto.requestDTO.CardPartnerReqDTO;
import com.kanban.back.dto.requestDTO.RequestTableReqDTO;
import com.kanban.back.dto.requestDTO.TagReqDTO;
import com.kanban.back.entity.*;
import com.kanban.back.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DetailPageService {
    CardRepository cardRepository;
    TagRepository tagRepository;
    CardPartnerRepository cardPartnerRepository;
    BoardUserRepository boardUserRepository;
    RequestTableRepository requestTableRepository;

    @Autowired
    DetailPageService(CardRepository cardRepository, TagRepository tagRepository, CardPartnerRepository cardPartnerRepository, BoardUserRepository boardUserRepository, RequestTableRepository requestTableRepository){
        this.cardRepository = cardRepository;
        this.tagRepository = tagRepository;
        this.cardPartnerRepository = cardPartnerRepository;
        this.boardUserRepository = boardUserRepository;
        this.requestTableRepository = requestTableRepository;
    }
    public CardDetailDTO getCardDetail(Integer c_id){
        Card card = cardRepository.getById(c_id);
            CardDetailDTO cardDetailDTO = card.toDetailDTO();
            // d_day 추가 코드
            if(Objects.nonNull(card.getC_start_date()) && Objects.nonNull(card.getC_end_date())) {
                long d_day = ChronoUnit.DAYS.between(card.getC_start_date(), card.getC_end_date());
                cardDetailDTO.setD_day(d_day);
            }
            return cardDetailDTO;
    }
    public List<UserTableDetailDTO> getBoardUsers(Integer b_id){
        List<BoardUser> boardUsers = boardUserRepository.getBoardUsersByB_id(b_id);
        List<UserTable> userTables = boardUsers.stream().map(s->s.getUserTable()).toList();
        return userTables.stream().map(s->s.toDetailDTO()).toList();
    }
    public void createCardMember(List<CardPartnerReqDTO> cardPartnerReqDTOs){
            for (CardPartnerReqDTO cardPartnerReqDTO : cardPartnerReqDTOs) {
                CardPartner cardPartner = cardPartnerReqDTO.toEntity();
                cardPartnerRepository.save(cardPartner);
            }
    }

    public void createTag(List<TagReqDTO> tagReqDTOs){
            for (TagReqDTO tagReqDTO : tagReqDTOs) {
                Tag tag = tagReqDTO.toEntity();
                tagRepository.save(tag);
            }
    }

    public void requetst(RequestTableReqDTO requestTableReqDTO){
        RequestTable requestTable = requestTableReqDTO.toEntity();
        requestTableRepository.save(requestTable);
    }
}
