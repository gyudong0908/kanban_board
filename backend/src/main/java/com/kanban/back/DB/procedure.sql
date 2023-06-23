DELIMITER $$
CREATE PROCEDURE get_id_procedure(user varchar(45))
BEGIN
select *
from board b
where b.b_id in (SELECT u_1.b_id
				FROM user_table u_0
				join board_user u_1
				on u_0.u_id = u_1.u_id
				where u_0.u_id = user)
order by b.b_name asc
limit 1;
END$$

DELIMITER $$
CREATE PROCEDURE totalcomplete(b_id int)
BEGIN
select (select count(*) from card c join task t on t.t_id = c.t_id where t.t_name="done" and c.b_id = b_id) / count(*)
from card c
where c.b_id = b_id;
END$$

DELIMITER $$
CREATE PROCEDURE privatecomplete(b_id int, user varchar(45))
BEGIN
select count(*) / (select count(*) from card c join card_partner cp on c.c_id = cp.c_id where cp.u_id = user and c.b_id = b_id)
from card c
join card_partner cp
on c.c_id = cp.c_id
join task t
on t.t_id = c.c_id
where cp.u_id  = user
and t.t_name = "done"
and c.b_id = b_id;
END$$


DELIMITER $$
CREATE PROCEDURE restore_card(c_id int)
BEGIN
-- card 복원
INSERT INTO card (c_title,b_admin,c_position,c_create_date,c_creator,c_id,t_id,b_id,c_upd_p,c_del_p,c_upd_date,c_description,c_start_date,c_end_date)
SELECT c_title,b_admin,c_position,c_create_date,c_creator,c_id,t_id,b_id,c_upd_p,c_del_p,c_upd_date,c_description,c_start_date,c_end_date
FROM deletedCardTBL d
WHERE d.c_id = c_id;
DELETE FROM deletedCardTBL WHERE c_id = c_id;

-- comment 복원
INSERT INTO comment (c_id, u_id, comment_id, comment_date, comment_contents)
SELECT c_id, u_id, comment_id, comment_date, comment_contents
FROM deletedCommentTBL d
WHERE d.c_id = c_id;
DELETE FROM deletedCommentTBL WHERE c_id = c_id;

-- files 복원
INSERT INTO files (file_id, file_name, file_path, file_ext, file_size, file_save_date, c_id, file_original_name)
SELECT file_id, file_name, file_path, file_ext, file_size, file_save_date, c_id, file_original_name
FROM deletedFilesTBL d
WHERE d.c_id = c_id;
DELETE FROM deletedFilesTBL WHERE c_id = c_id;
END$$

DELIMITER $$
CREATE PROCEDURE restore_calendar(cal_id int)
BEGIN
-- calendar 복원
INSERT INTO calendar (cal_id,cal_content,cal_date,writer)
SELECT cal_id,cal_content,cal_date,writer
FROM deletedCalendarTBL d
WHERE d.cal_id = cal_id;
DELETE FROM deletedCalendarTBL WHERE cal_id = cal_id;
END$$

DELIMITER $$
CREATE PROCEDURE restore_files(file_id int)
BEGIN
-- files 복원
INSERT INTO files (file_id, file_name, file_path, file_ext, file_size, file_save_date, c_id,file_original_name)
SELECT file_id, file_name, file_path, file_ext, file_size, file_save_date, c_id,file_original_name
FROM deletedFilesTBL d
WHERE d.file_id = file_id;
DELETE FROM deletedFilesTBL WHERE file_id = file_id;
END$$


