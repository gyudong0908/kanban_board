package com.kanban.back.service;

import com.kanban.back.Exception.AlreadyExist;
import com.kanban.back.Exception.FileStorageException;
import com.kanban.back.Exception.NotFindData;
import com.kanban.back.dto.reponseDTO.mainpageDTO.BoardMainDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.CardMainDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.TaskMainDTO;
import com.kanban.back.dto.reponseDTO.mainpageDTO.UserTableMainDTO;
import com.kanban.back.dto.requestDTO.BoardReqDTO;
import com.kanban.back.dto.requestDTO.BoardUserReqDTO;
import com.kanban.back.dto.requestDTO.CardReqDTO;
import com.kanban.back.dto.requestDTO.TaskReqDTO;
import com.kanban.back.entity.*;
import com.kanban.back.repository.BoardRepository;
import com.kanban.back.repository.CardRepository;
import com.kanban.back.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class MainPageService {
    BoardRepository boardRepository;
    TaskRepository taskRepository;
    CardRepository cardRepository;

    @Autowired
    MainPageService(BoardRepository boardRepository, TaskRepository taskRepository, CardRepository cardRepository){
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
        this.cardRepository = cardRepository;
    }

    @Transactional
    public BoardMainDTO createBoard(BoardReqDTO boardReqDTO){
            Board board = boardReqDTO.toEntity();
            boardRepository.save(board);
            defaultTask(boardRepository.getById(board.getB_id()));
            return board.toMainDTO();
    }

    @Transactional
    public BoardMainDTO getBoard(String u_id){
        Board board = boardRepository.get_id_procedure(u_id);
            BoardMainDTO boardMainDTO = board.toMainDTO();
            // boardNames 추가 하는 코드
            List<Map<String, Object>> boardNames = boardRepository.getBoardName(u_id);
            Iterator<Map<String, Object>> iterator = boardNames.iterator();

            while (iterator.hasNext()) {
                Map<String, Object> boardName = iterator.next();
                if (boardName.get("b_id") == (board.getB_id())) {
                    iterator.remove();
                }
            }
            boardMainDTO.setBoradnames(boardNames);

//        // board에 속해있는 user정보 추가하는 코드
            List<UserTableMainDTO> userTables = new ArrayList<>();
            for (BoardUser boardUser : board.getBoardUsers()) {
                userTables.add(boardUser.getUserTable().toMainDTO());
            }
            boardMainDTO.setUserTables(userTables);
            return boardMainDTO;
    }

    public BoardMainDTO getBoardB_id(Integer b_id){
        BoardMainDTO boardMainDTO = boardRepository.getById(b_id).toMainDTO();
            return boardMainDTO;
    }

    @Transactional
    public BoardMainDTO updateBoard(BoardReqDTO boardReqDTO){
        Board board = boardRepository.getById(boardReqDTO.getB_id());
            board.update(boardReqDTO);
            return board.toMainDTO();
    }

    public void deleteBoard(Integer b_id){
        Board board = boardRepository.getById(b_id);
        boardRepository.deleteById(board.getB_id());
    }

    @Transactional
    public TaskMainDTO createTask(TaskReqDTO taskReqDTO){
        Task task = taskReqDTO.toEntity();
        taskRepository.save(task);
        return task.toMainDTO();
    }

    @Transactional
    public TaskMainDTO updateTask(TaskReqDTO taskReqDTO){
        Task task = taskRepository.getById(taskReqDTO.getT_id());
        task.update(taskReqDTO);
        return task.toMainDTO();

    }

    public void deleteTask(Integer t_id){
        taskRepository.deleteById(t_id);
    }
    @Transactional
    public CardMainDTO createCard(CardReqDTO cardReqDTO) {
        Card card = cardReqDTO.toEntity();
        cardRepository.save(card);
        return card.toMainDTO();
    }
    @Transactional
    public CardMainDTO updateCard(CardReqDTO cardReqDTO){
        Card card = cardRepository.getById(cardReqDTO.getC_id());
        card.update(cardReqDTO);
        return card.toMainDTO();
    }
    @Transactional
    public void deleteCard(Integer c_id){
        cardRepository.deleteById(c_id);
    }

    @Transactional
    public CardMainDTO restoreCard(Integer c_id){
        cardRepository.restoreCard(c_id);
        return cardRepository.getById(c_id).toMainDTO();
    }

    // 초기 task4개 만들기
    public void defaultTask(Board board) {
        int i = 1;
        List<String> names = Arrays.asList("Todo", "Doing", "Test", "Done");
        TaskReqDTO taskReqDTO = new TaskReqDTO();
        taskReqDTO.setB_id(board.getB_id());
        taskReqDTO.setT_creator("admin");
        taskReqDTO.setT_type("git");

        for (String name : names) {
            taskReqDTO.setT_name(name);
            taskReqDTO.setT_position(i++);
            Task task = taskReqDTO.toEntity();
            taskRepository.save(task);
            }
        }
}
