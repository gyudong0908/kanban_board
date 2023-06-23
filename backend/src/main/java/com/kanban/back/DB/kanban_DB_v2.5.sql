-- schema 이름: kanbanboard(가제)
-- DB version2.1 내용 : 1.issue table -> comment table 
-- 2. task b_admin 삭제
-- 3. board_user table에서 u_email, u_name 삭제 
-- 4. 각 테이블 auto increment 적용 (id varchar-> int)
-- 5. date -> datetime type 변환

-- DB version2.2 내용 : c_description, comment_content type 'text'로 변경
-- board_group을 card_partner로 변경 및 pk g_id -> partner id로 변경
-- board_user에 pk 추가 board_user_id

-- version2.3 내용 : calendar table 생성 

-- vsersion 2.5 내용 : file table 생성


use kanban;
create table board (	
	b_name VARCHAR (100) NOT NULL comment "board 이름",
	b_goal VARCHAR (100) comment "board 목표",
    b_id int NOT NULL AUTO_INCREMENT PRIMARY key comment "board id",
    b_create_date datetime NOT NULL comment "board 생성날짜",
    b_upd_date datetime comment "board 수정일자",
    b_creator VARCHAR (100) NOT NULL comment "board 생성자",
    b_admin VARCHAR(100) NOT NULL comment "board 관리자"
);


-- task 테이블 생성
-- pk: t_id
-- fk: (board)b_id
CREATE TABLE task(
    t_name VARCHAR (100) NOT NULL comment "task 이름",
    t_create_date datetime  NOT NULL comment "task 생성날짜",
    t_type VARCHAR (100) NOT NULL comment "task 종류",
    t_upd_date datetime  comment "task 수정날짜",
    t_creator VARCHAR (100) NOT NULL comment "task 생성자",
    t_upd_p VARCHAR (100) comment "task 수정자",
    t_del_p VARCHAR (100) comment "task 삭제자",
    t_id int NOT NULL AUTO_INCREMENT Primary Key comment "task ID",
    b_id int NOT NULL comment "board ID",
    constraint foreign Key (b_id) references board(b_id),
    t_position int (100) NOT NULL comment "task 순서"
);

-- card 테이블 생성 
-- pk: c_id
-- fk: (task)t_id, (board)b_id
CREATE TABLE card(
    c_title VARCHAR(100) NOT NULL comment "card 제목",
    b_admin VARCHAR(100) comment "board 관리자",
    c_position int NOT NULL comment "card 순서",
    c_create_date datetime NOT NULL comment "card 생성닐찌",
    c_creator VARCHAR(100) NOT NULL comment "card 생성자",
    c_id int NOT NULL AUTO_INCREMENT primary key comment "card ID" ,
    t_id int NOT NULL comment "task ID",
    constraint foreign key (t_id) references task(t_id),
    b_id int NOT NULL comment "board ID",
    constraint foreign key (b_id) references board(b_id),
    c_upd_p VARCHAR(100) comment "card 수정자",
    c_del_p VARCHAR(100) comment "card 삭제자",
    c_upd_date datetime comment "card 수정날짜",
    c_description TEXT comment "card 설명",
    c_start_date date comment "업무 시작 날짜",
    c_end_date date comment "업무 마감 날짜"
 );


-- Tag 테이블 생성
-- pk: tag_id, fk: c_id
CREATE TABLE tag (
    c_id int NOT NULL comment "card ID",
    constraint foreign key (c_id) references card (c_id),
    tag_id int NOT NULL AUTO_INCREMENT primary Key comment "tag ID",
    tag_name VARCHAR(100) NOT NULL comment "tag 이름",
    tag_color int  NOT NULL comment "tag 색상"
);


-- 유저 테이블 생성 
-- pk: u_id, u_email
CREATE TABLE user_table(
    u_date_join datetime NOT NULL comment "가입 날짜",
    u_name VARCHAR (100) NOT NULL comment "사용자 이름",
    u_email VARCHAR (100) NOT NULL comment "사용자 이메일",
    u_id VARCHAR (100) NOT NULL primary key  comment "사용자 ID"
);

CREATE TABLE board_user(
    b_id int NOT NULL comment "board ID",
    constraint foreign key (b_id) references board (b_id),
    u_id VARCHAR (100) NOT NULL comment "사용자 ID",
    constraint foreign key (u_id) references user_table(u_id),
    unique(b_id,u_id),
    board_user_id int NOT NULL AUTO_INCREMENT primary key 
);

CREATE TABLE  comment(
    c_id int NOT NULL comment "card ID",
    constraint foreign key (c_id) references card (c_id),
    u_id VARCHAR (100) NOT NULL comment "사용자 ID" ,
    constraint foreign key (u_id) references user_table (u_id),
    comment_id int NOT NULL AUTO_INCREMENT primary key comment "댓글 ID", 
    comment_date datetime  NOT NULL comment "댓글 생성날짜",
    comment_contents TEXT NOT NULL comment "댓글 내용"
);

-- CREATE TABLE tmp_table(
-- 	tmp_id int 	NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "tmp_table ID",
-- 	c_id int NOT NULL comment "card ID",
--     constraint foreign key (c_id) references card(c_id),
--     u_id VARCHAR (100) NOT NULL comment "사용자 ID" ,
--     constraint foreign key (u_id) references user_table (u_id),
--     commit_status VARCHAR (100) NOT NULL comment "완료상태"
--     );
    
CREATE TABLE card_partner (
	b_id int NOT NULL comment "board ID",
    constraint foreign key (b_id) references board(b_id),
    c_id int NOT NULL comment "card ID",
    constraint foreign key (c_id) references card(c_id),
    u_id VARCHAR (100) NOT NULL comment "사용자 ID" ,
    constraint foreign key (u_id) references user_table (u_id),
    unique(u_id,c_id),
    partner_id int NOT NULL AUTO_INCREMENT primary key
);

create table calendar (
cal_id int not null auto_increment primary key comment "calendar ID",
cal_content text comment "schedule 내용",
cal_date date comment "개별 날짜",
writer varchar(25) comment "작성자"
);

create table request_table (
request_id int not null auto_increment primary key comment "request ID",
request_date datetime comment "요청 날짜",
request_user varchar(100) comment "요청 보낸 사람",
c_id int NOT NULL comment "card ID",
constraint foreign key (c_id) references card (c_id)
);

create table files (
file_id int not null auto_increment primary key comment "file id",
file_name varchar(100) comment "file 이름",
file_path varchar(100) comment "file 저장 경로",
file_ext varchar(10) comment "file 확장자",
file_size int comment "file 크기",
file_save_date datetime comment "파일 저장 날짜",
c_id int NOT NULL comment "card ID",
constraint foreign key (c_id) references card (c_id),
file_original_name VARCHAR(100) comment "파일 실제 이름"
);

