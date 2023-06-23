package com.kanban.back.Exception;

public class NotFindData extends RuntimeException{
    public NotFindData(String kind){super("해당" + kind + "를 찾을 수 없습니다");}
}
