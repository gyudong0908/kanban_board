package com.kanban.back.Exception;

public class AlreadyExist extends RuntimeException{
    public AlreadyExist(String kind) {super("이미 존재하는 " + kind + "입니다");}
}