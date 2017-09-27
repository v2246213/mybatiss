USE wangwang;

DELIMITER $$

DROP TRIGGER IF EXISTS cover_use_conut$$
CREATE 
	DEFINER = 'root'@'%'
TRIGGER cover_use_conut
	AFTER INSERT
	ON t_group
	FOR EACH ROW
BEGIN
  UPDATE t_sys_picture_library SET total_use_count = total_use_count + 1,today_use_count = today_use_count + 1, update_time = now() WHERE pic_type = 1 AND pic_url = NEW.group_cover_plan;
  IF NEW.pid != 0 THEN
    UPDATE t_sys_tag SET total_use_count = total_use_count + 1,today_use_count = today_use_count + 1, update_time = now() WHERE name = NEW.group_name;
  END IF;
END
$$

DELIMITER ;

DELIMITER $$

DROP TRIGGER IF EXISTS join_group_update_msg_state$$
CREATE 
	DEFINER = 'root'@'%'
TRIGGER join_group_update_msg_state
	AFTER INSERT
	ON t_group_member
	FOR EACH ROW
BEGIN
 SET @pid =
(SELECT
    pid
  FROM t_group
  WHERE id = NEW.group_id);
  IF @pid = 0 THEN
UPDATE t_sys_msg
SET pass = 1
WHERE pass = 0
AND type = 3
AND object_id = NEW.group_id
AND receive_user_id = NEW.user_id;
  END IF;
END
$$

DELIMITER ;

DELIMITER $$

DROP TRIGGER IF EXISTS sys_tag_today_use_count$$
CREATE 
	DEFINER = 'root'@'%'
TRIGGER sys_tag_today_use_count
	BEFORE UPDATE
	ON t_sys_tag
	FOR EACH ROW
BEGIN
  IF NEW.today_use_count != OLD.today_use_count AND TO_DAYS(NEW.update_time) != TO_DAYS(OLD.update_time) THEN
    SET NEW.today_use_count = 1;
  END IF;
END
$$

DELIMITER ;

DELIMITER $$

DROP TRIGGER IF EXISTS today_use_count$$
CREATE 
	DEFINER = 'root'@'%'
TRIGGER today_use_count
	BEFORE UPDATE
	ON t_sys_picture_library
	FOR EACH ROW
BEGIN
  IF NEW.today_use_count != OLD.today_use_count AND TO_DAYS(NEW.update_time) != TO_DAYS(OLD.update_time) THEN
    SET NEW.today_use_count = 1;
  END IF;
END
$$

DELIMITER ;