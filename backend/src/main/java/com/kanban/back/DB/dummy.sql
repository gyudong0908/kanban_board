-- Board dummy data 
use kanban;
DELIMITER $$
CREATE PROCEDURE testBoardDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 30 DO
        INSERT INTO board(b_name, b_goal, b_id, b_create_date, b_upd_date,b_creator,b_del_yn,b_admin)
          VALUES(concat('name',i),concat('goal',i),i,now(),now(),concat('작성자',i),'no',concat('관리자',i));
        SET i = i + 1;
    END WHILE;
END $$
DELIMITER ;
call testBoardDataInsert();

-- task dummy
DELIMITER $$
CREATE PROCEDURE testTaskDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    DECLARE name varchar(10);
    
    WHILE i <= 30 DO
    while j <=4 Do
		case
			when (j=1) then set name = "Todo";
            when (j=2) then set name = "doing";
            when (j=3) then set name = "test";
            when (j=4) then set name = "done";
         end case;
    
        INSERT INTO task(t_name, t_create_date, t_type, t_upd_date, t_creator, t_upd_p, t_del_p, b_id,t_del_yn, t_position)
          VALUES(name, now(),concat('type',k), now(), concat('creator',k),concat('update_person',k),concat('delete_person',k),i,'no', j);
        SET j = j + 1;
        SET k = k + 1;
    END WHILE;
	SET j = 1;
    SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testTaskDataInsert();

-- Card dummy 
DELIMITER $$
CREATE PROCEDURE testCardDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    DECLARE l INT DEFAULT 1;
    WHILE i <= 120 DO 
		WHILE j <= 4 DO 
        INSERT INTO card(c_title,b_admin,c_position,c_create_date,c_creator,t_id,b_id,c_upd_p,c_del_p,c_upd_date,c_description,c_del_yn,c_start_date,c_end_date)
          VALUES(concat('title',k),concat('관리자',l),j,now(),concat('작성자',k),i,l,concat('수정자',k),concat('삭제자',k),now(),concat('설명',k),'no',"2023-03-01","2023-05-28");
        SET j = j + 1;
        SET k = k + 1;
    END WHILE;
    	SET j = 1;
		IF i != 120 THEN
			SET l = CASE WHEN (i % 4) = 0 THEN l + 1 ELSE l END;
		END IF;
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testCardDataInsert();

-- user_table dummy
DELIMITER $$
CREATE PROCEDURE testUserTableDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 120 DO 
    
        INSERT INTO user_table(u_date_join,u_name,u_email,u_id)
          VALUES(curdate(),concat('user',i),concat('user',i,'@kanban.com'),concat('userId',i));
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testUserTableDataInsert();

-- Board_user dummy 
DELIMITER $$
CREATE PROCEDURE testBoardUserDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    WHILE i <= 30 DO
		WHILE j <= 4 DO
			INSERT INTO board_user(b_id,u_id)
			VALUES(i,concat('userId',k));
            SET j = j + 1;
            SET k = k + 1;
		end while;
        SET j = 1;
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testBoardUserDataInsert();

-- card_partner dummy 
DELIMITER $$
CREATE PROCEDURE testCardPartnerDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    DECLARE l INT DEFAULT 1;
    
    WHILE i <= 30 DO
		WHILE j <= 16 DO
			INSERT INTO card_partner(b_id,c_id,u_id)
			VALUES(i,k,concat('userId',(i-1)*4+(l)));
            SET l = (j % 4) + 1;
			SET j = j + 1;
            SET k = k + 1;
		end while;
        SET j = 1;
        SET i = i + 1;
	END WHILE;
END$$
DELIMITER ;
call testCardPartnerDataInsert();

-- comment dummy
DELIMITER $$
CREATE PROCEDURE testCommentDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 1;
    DECLARE k INT DEFAULT 1;
    DECLARE l INT DEFAULT 1;
    WHILE i <= 30 DO
		WHILE j <= 16 DO
			INSERT INTO comment(c_id,u_id,del_yn,comment_date,comment_contents)
			  VALUES(k,concat('userId',(i-1)*4+(l)),'no',now(),concat('blahblah',k));
			SET l = (j % 4) + 1;
			SET j = j + 1;
            SET k = k + 1;
		END WHILE;
        SET j = 1;
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testCommentDataInsert();

-- tag dummy
DELIMITER $$
CREATE PROCEDURE testTagDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 480 DO 
        INSERT INTO tag(c_id,tag_id,tag_name,tag_color)
          VALUES(i,i,concat('tagname',i),i%4+1);
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testTagDataInsert();

-- tmp_table dummy
DELIMITER $$
CREATE PROCEDURE testTempTableDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 30 DO 
        INSERT INTO tmp_table(tmp_id,c_id,u_id,commit_status)
          VALUES(i,i,concat('user',i),concat('commit',i));
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;
call testTempTableDataInsert();

