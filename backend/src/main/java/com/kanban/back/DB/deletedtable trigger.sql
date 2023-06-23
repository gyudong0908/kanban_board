-- 삭제 데이터 보관 테이블 트리거 
use kanban;
-- Board 
-- CREATE TABLE deletedBoardTBL (
-- 	b_name VARCHAR (100) NOT NULL comment "board 이름",
-- 	b_goal VARCHAR (100) comment "board 목표",
--     b_id int NOT NULL AUTO_INCREMENT PRIMARY key comment "board id",
--     b_create_date datetime NOT NULL comment "board 생성날짜",
--     b_upd_date datetime comment "board 수정일자",
--     b_creator VARCHAR (100) NOT NULL comment "board 생성자",
--     b_del_yn VARCHAR (100) NOT NULL comment "board 삭제여부",
--     b_admin VARCHAR(100) NOT NULL comment "board 관리자",
--     deletedDate DATETIME 
--     );
--     
-- DELIMITER //
-- CREATE TRIGGER trg_deletedBoardTBL
-- AFTER DELETE
-- ON board
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedBoardTBL
-- VALUES(OLD.b_name, OLD.b_id, OLD.b_create_date, OLD.b_upd_date, OLD.b_creator, OLD.b_del_yn, OLD.b_admin, now());
-- END //
-- DELIMITER ;

-- board_user
-- CREATE TABLE deletedBoard_userTBL(
--     b_id int NOT NULL comment "board ID",
--     constraint foreign key (b_id) references board (b_id),
--     u_id VARCHAR (100) NOT NULL comment "사용자 ID",
--     constraint foreign key (u_id) references user_table(u_id),
--     unique(b_id,u_id),
--     board_user_id int NOT NULL AUTO_INCREMENT primary key,
--     deletedDate DATETIME 
--     );
--     
-- DELIMITER //
-- CREATE TRIGGER trg_deletedBoard_userTBL
-- AFTER DELETE
-- ON board_user
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedBoard_userTBL
-- VALUES(OLD.b_id, OLD.u_id, OLD.board_user_id, now());
-- END //
-- DELIMITER ;

-- card
CREATE TABLE deletedCardTBL(
    c_title VARCHAR(100) NOT NULL comment "card 제목",
    b_admin VARCHAR(100) comment "board 관리자",
    c_position int NOT NULL comment "card 순서",
    c_create_date datetime NOT NULL comment "card 생성닐찌",
    c_creator VARCHAR(100) NOT NULL comment "card 생성자",
    c_id int NOT NULL AUTO_INCREMENT primary key comment "card ID" ,
    t_id int NOT NULL comment "task ID",
    -- constraint foreign key (t_id) references task(t_id), 
    b_id int NOT NULL comment "board ID",
    -- constraint foreign key (b_id) references board(b_id),
    c_upd_p VARCHAR(100) comment "card 수정자",
    c_del_p VARCHAR(100) comment "card 삭제자",
    c_upd_date datetime comment "card 수정날짜",
    c_description TEXT comment "card 설명",
    c_start_date date comment "업무 시작 날짜",
    c_end_date date comment "업무 마감 날짜",
    deletedDate DATETIME
    );
    
DELIMITER //
CREATE TRIGGER trg_deletedCardTBL
AFTER DELETE
ON card
FOR EACH ROW
BEGIN
INSERT INTO deletedCardTBL
VALUES(old.c_title, old.b_admin, old.c_position, old.c_create_date, old.c_creator, old.c_id, old.t_id, old.b_id, old.c_upd_p, old.c_del_p, old.c_upd_date, old.c_description, old.c_start_date, old.c_end_date, now());
END //
DELIMITER ;

-- card partner
-- CREATE TABLE deletedCard_partnerTBL(
-- 	b_id int NOT NULL comment "board ID",
--     constraint foreign key (b_id) references board(b_id),
--     c_id int NOT NULL comment "card ID",
--     constraint foreign key (c_id) references card(c_id),
--     u_id VARCHAR (100) NOT NULL comment "사용자 ID" ,
--     constraint foreign key (u_id) references user_table (u_id),
--     unique(u_id,c_id),
--     partner_id int NOT NULL AUTO_INCREMENT primary key,
--     deletedDate DATETIME
--     );
--     
-- DELIMITER //
-- CREATE TRIGGER trg_deletedCard_partnerTBL
-- AFTER DELETE
-- ON card_partner
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedCard_partnerTBL
-- VALUES(OLD.b_id, OLD.c_id, OLD.u_id,OLD.partner_id, now());
-- END //
-- DELIMITER ;
    
-- comment
CREATE TABLE deletedCommentTBL(
    c_id int NOT NULL comment "card ID",
    -- constraint foreign key (c_id) references card (c_id),
    u_id VARCHAR (100) NOT NULL comment "사용자 ID" ,
    -- constraint foreign key (u_id) references user_table (u_id),
    comment_id int NOT NULL AUTO_INCREMENT primary key comment "댓글 ID", 
    comment_date datetime  NOT NULL comment "댓글 생성날짜",
    comment_contents TEXT NOT NULL comment "댓글 내용",
    deletedDate DATETIME
    );
    
DELIMITER //
CREATE TRIGGER trg_deletedCommentTBL
AFTER DELETE
ON comment
FOR EACH ROW
BEGIN
INSERT INTO deletedCommentTBL
VALUES(OLD.c_id, OLD.u_id, OLD.comment_id,OLD.comment_date,OLD.comment_contents, now());
END //
DELIMITER ;

-- tag
-- CREATE TABLE deletedTagTBL(
-- 	c_id int NOT NULL comment "card ID",
--     constraint foreign key (c_id) references card (c_id),
--     tag_id int NOT NULL AUTO_INCREMENT primary Key comment "tag ID",
--     tag_name VARCHAR(100) NOT NULL comment "tag 이름",
--     tag_color int  NOT NULL comment "tag 색상",
--     deletedDate DATETIME
--     );
--     
-- DELIMITER //
-- CREATE TRIGGER trg_deletedTagTBL
-- AFTER DELETE
-- ON tag
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedTagTBL
-- VALUES(OLD.c_id, OLD.tag_id, OLD.tag_name,OLD.tag_color, now());
-- END //
-- DELIMITER ;


-- task
-- CREATE TABLE deletedTaskTBL(
-- 	t_name VARCHAR (100) NOT NULL comment "task 이름",
--     t_create_date datetime  NOT NULL comment "task 생성날짜",
--     t_type VARCHAR (100) NOT NULL comment "task 종류",
--     t_upd_date datetime  comment "task 수정날짜",
--     t_creator VARCHAR (100) NOT NULL comment "task 생성자",
--     t_upd_p VARCHAR (100) comment "task 수정자",
--     t_del_p VARCHAR (100) comment "task 삭제자",
--     t_id int NOT NULL AUTO_INCREMENT Primary Key comment "task ID",
--     b_id int NOT NULL comment "board ID",
--     constraint foreign Key (b_id) references board(b_id),
--     t_del_yn VARCHAR (100) NOT NULL comment "삭제 여부",
--     t_position int (100) NOT NULL comment "task 순서",
--     deletedDate DATETIME
--     );


-- DELIMITER //
-- CREATE TRIGGER trg_deletedTaskTBL
-- AFTER DELETE
-- ON task
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedTaskTBL
-- VALUES(OLD.t_name, OLD.t_create_date, OLD.t_type, OLD.t_upd_date, OLD.t_creator, OLD.t_upd_p, OLD.t_del_p, OLD.t_id, OLD.b_id, OLD.t_del_yn, OLD.t_position, now());
-- END //
-- DELIMITER ; 

-- tmp_table
-- CREATE TABLE deletedTmp_tableTBL(
-- tmp_id int 	NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "tmp_table ID",
-- 	c_id int NOT NULL comment "card ID",
--     constraint foreign key (c_id) references card(c_id),
--     u_id VARCHAR (100) NOT NULL comment "사용자 ID" ,
--     constraint foreign key (u_id) references user_table (u_id),
--     commit_status VARCHAR (100) NOT NULL comment "완료상태",
--     deletedDate DATETIME
--     );
    
-- DELIMITER //
-- CREATE TRIGGER trg_deletedTmp_tableTBL
-- AFTER DELETE
-- ON tmp_table
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedTmp_tableTBL
-- VALUES(OLD.tmp_id, OLD.c_id, OLD.u_id, OLD.commit_status, now());
-- END //
-- DELIMITER ; 

-- user_table
-- CREATE TABLE deletedUser_tableTBL(
-- 	u_date_join datetime NOT NULL comment "가입 날짜",
--     u_name VARCHAR (100) NOT NULL comment "사용자 이름",
--     u_email VARCHAR (100) NOT NULL comment "사용자 이메일",
--     u_id VARCHAR (100) NOT NULL primary key  comment "사용자 ID",
--     deletedDate DATETIME
--     );


-- DELIMITER //
-- CREATE TRIGGER trg_deletedUser_tableTBL
-- AFTER DELETE
-- ON user_table
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedUser_tableTBL
-- VALUES(OLD.u_date_join, OLD.u_name, OLD.u_email, OLD.u_id, now());
-- END //
-- DELIMITER ; 

-- calendar
CREATE TABLE deletedCalendarTBL(
	cal_id int not null auto_increment primary key comment "calendar ID",
	cal_content text comment "schedule 내용",
	cal_date date comment "개별 날짜",
	writer varchar(25) comment "작성자",
    deletedDate DATETIME
    );
    
DELIMITER //
CREATE TRIGGER trg_deletedCalendarTBL
AFTER DELETE
ON calendar
FOR EACH ROW
BEGIN
INSERT INTO deletedCalendarTBL
VALUES(old.cal_id,old.cal_content,old.cal_date,old.writer,now());
END //
DELIMITER ; 

-- request_table
-- CREATE TABLE deletedRequest_tableTBL(
-- 	request_id int not null auto_increment primary key comment "request ID",
-- 	request_date datetime comment "요청 날짜",
-- 	request_user varchar(100) comment "요청 보낸 사람",
-- 	c_id int NOT NULL comment "card ID",
-- 	constraint foreign key (c_id) references card (c_id),
--     deletedDate DATETIME
--     );
--     
-- DELIMITER //
-- CREATE TRIGGER trg_deletedRequest_tableTBL
-- AFTER DELETE
-- ON request_table
-- FOR EACH ROW
-- BEGIN
-- INSERT INTO deletedRequest_tableTBL
-- VALUES(old.request_id, old.request_date, old.request_user, old.c_id,now());
-- END //
-- DELIMITER ; 

-- files
CREATE TABLE deletedFilesTBL(
	file_id int not null auto_increment primary key comment "file id",
	file_name varchar(100) comment "file 이름",
	file_path varchar(100) comment "file 저장 경로",
	file_ext varchar(10) comment "file 확장자",
	file_size int comment "file 크기",
	file_save_date datetime comment "파일 저장 날짜",
	c_id int NOT NULL comment "card ID",
	-- constraint foreign key (c_id) references card (c_id),
    file_original_name VARCHAR(100) comment "파일 실제 이름",
    deletedDate DATETIME
    );
    
DELIMITER //
CREATE TRIGGER trg_deletedFilesTBL
AFTER DELETE
ON files
FOR EACH ROW
BEGIN
INSERT INTO deletedFilesTBL
VALUES(old.file_id,old.file_name,old.file_path,old.file_ext,old.file_size,old.file_save_date,old.c_id,old.file_original_name,now());
END //
DELIMITER ; 

-- EVENT 실행 되게 설정
SET GLOBAL event_scheduler = ON ;

-- 30일 뒤 삭제
CREATE EVENT delete_card_data
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP + INTERVAL 1 DAY  
DO
    DELETE FROM deletedCardTBL WHERE deletedDate < DATE_SUB(NOW(), INTERVAL 30 DAY);
    
CREATE EVENT delete_commtent_data
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP + INTERVAL 1 DAY  
DO
    DELETE FROM deletedCommentTBL WHERE deletedDate < DATE_SUB(NOW(), INTERVAL 30 DAY);
    
-- CREATE EVENT delete_files_data
-- ON SCHEDULE EVERY 1 DAY
-- STARTS CURRENT_TIMESTAMP + INTERVAL 1 DAY  
-- DO
--     DELETE FROM deletedFilesTBL WHERE deletedDate < DATE_SUB(NOW(), INTERVAL 30 DAY);
    
CREATE EVENT delete_calendar_data
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP + INTERVAL 1 DAY  
DO
    DELETE FROM deletedCalendarTBL WHERE deletedDate < DATE_SUB(NOW(), INTERVAL 30 DAY);