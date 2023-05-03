/*
 Gender: 0 => Male; 1 => Female
 isActive, ... (True or fasle): 0 => False; 1 => True
 */
use demo;
DROP DATABASE IF EXISTS `TopicRegistration`;

CREATE DATABASE `TopicRegistration`;

USE `TopicRegistration`;

DROP TABLE IF EXISTS `Major`;
CREATE TABLE `Major` (
    major_id CHAR(12),
    major_name NVARCHAR(255),
    is_active BIT DEFAULT 1,-- 0: chưa được active, 1: đã được active
    `description` NVARCHAR(255),
    PRIMARY KEY `PK_Major`(major_id)
);

DROP TABLE IF EXISTS `Person`;
CREATE TABLE IF NOT EXISTS `Person` (
    person_id CHAR(12),
    full_name NVARCHAR(255),
    gender BIT DEFAULT 0,-- 0: nữ, 1: nam
    `address` NVARCHAR(255),
    phonenumber CHAR(10),
    email CHAR(255),
	`role` CHAR(50) NOT NULL,-- admin/student/teacher
    -- Người dùng còn tồn tại hay không
    -- is_active BIT DEFAULT(1),-- 0: chưa được active, 1: đã được active
    `description` NVARCHAR(255),
    is_deleted BIT DEFAULT 0, -- 0 là mặc định là chưa xóa, 1 là đã xóa(được đánh dấu là xóa mềm)
    PRIMARY KEY `PK_Person`(`person_id`)
);

DROP TABLE IF EXISTS `Teacher`;
CREATE TABLE `Teacher` (
    teacher_id CHAR(12),
    person_id CHAR(12),
    major_id CHAR(12),
    -- Có phải là trưởng bộ môn hay không, mặc định là không phải
    is_head BIT DEFAULT 0,
    PRIMARY KEY `PK_Teacher`(teacher_id),
    CONSTRAINT `FK_Teacher_Person` FOREIGN KEY (person_id) REFERENCES Person(person_id) ON UPDATE CASCADE ON DELETE
    SET
        NULL,
        CONSTRAINT `FK_Teacher_Major` FOREIGN KEY (major_id) REFERENCES Major(major_id) ON UPDATE CASCADE ON DELETE
    SET
        NULL
);

DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
    admin_id CHAR(12),
    person_id CHAR(12),
    PRIMARY KEY `PK_Admin`(admin_id),
    CONSTRAINT `FK_Admin_Person` FOREIGN KEY (person_id) REFERENCES Person(person_id) ON UPDATE CASCADE ON DELETE
    SET
    NULL
);

DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account` (
    account_id CHAR(12),
    username CHAR(255) UNIQUE NOT NULL,
    `password` CHAR(255),
    -- is_active BIT DEFAULT(0),-- 0: chưa được active, 1: đã được active
    `description` NVARCHAR(255),
    person_id CHAR(12),-- Dùng liên kết với bảng Person để lấy role( nhầm phân chia luồng người sử dụng)
    PRIMARY KEY `PK_Account`(account_id),
    CONSTRAINT `FK_Person_Account` FOREIGN KEY (person_id) REFERENCES Person(person_id) ON UPDATE CASCADE ON DELETE
    SET
        NULL
);

DROP TABLE IF EXISTS `Board`;
CREATE TABLE `Board` (
    board_id CHAR(12),
    board_name NVARCHAR(255),
    no_member INT,-- sô lượng thành viên trong hội đồng
    `description` NVARCHAR(255),
    is_deleted BIT DEFAULT 0, -- 0 là mặc định là chưa xóa, 1 là đã xóa(được đánh dấu là xóa mềm)
    -- is_active BIT DEFAULT(0),-- 0: chưa được active, 1: đã được active
    PRIMARY KEY `PK_Board`(board_id)
);

DROP TABLE IF EXISTS `TeacherBoard`;
CREATE TABLE `TeacherBoard` (
    teacher_id CHAR(12),
    board_id CHAR(12),
    is_deleted BIT DEFAULT 0, -- 0 là mặc định là chưa xóa, 1 là đã xóa(được đánh dấu là xóa mềm)
    PRIMARY KEY `FK_TeacherBoard`(teacher_id, board_id),
    CONSTRAINT `FK_TeacherBoard_Teacher` FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT `FK_TeacherBoard_Board` FOREIGN KEY (board_id) REFERENCES Board(board_id) ON UPDATE CASCADE ON DELETE CASCADE
);

DROP TABLE IF EXISTS `RegistrationPeriod`;
CREATE TABLE `RegistrationPeriod` (
    registration_period_id CHAR(12),
    registration_period_name CHAR(10),
    is_active BIT DEFAULT 0,
    `description` NVARCHAR(255),
    semeter INT,
    school_year INT,
    is_registration_teacher BIT NOT NULL, -- kiểm tra xem đây có phải là thời gian đăng kí của giáo viên hay không.0 là thời gian
    -- đăng kí của giáo viên. 1 là thời gian đăng kí của sinh viên.
    open_date DATETIME,
    close_date DATETIME,
    is_deleted BIT DEFAULT 0, -- 0 là mặc định là chưa xóa, 1 là đã xóa(được đánh dấu là xóa mềm)
    PRIMARY KEY `PK_RegistrationPeriod`(registration_period_id)
);

DROP TABLE IF EXISTS `Topic`;
CREATE TABLE `Topic` (
    topic_id CHAR(12),
    topic_name NVARCHAR(255),
    -- is_active BIT DEFAULT(0),
    `status` BIT DEFAULT 0,-- Kiểm tra xem trưởng bộ môn có duyệt đề tài này hay chưa.
    `description` NVARCHAR(255),
    is_selected BIT DEFAULT 0,-- khi có người chọn đề tài thì chuyển is_selected thành 1. Nếu ng đk hủy chọn thì 
    -- chuyển is_selected về 0
    is_full BIT DEFAULT 0,
    max_mo_member INT,
    major_id CHAR(12),
    teacher_id CHAR(12),
    registration_period_id CHAR(10),
    is_deleted BIT DEFAULT 0, -- 0 là mặc định là chưa xóa, 1 là đã xóa(được đánh dấu là xóa mềm)
    PRIMARY KEY `PK_Topic`(topic_id),
    CONSTRAINT `FK_Topic_Major` FOREIGN KEY (major_id) REFERENCES Major(major_id) ON UPDATE CASCADE ON DELETE
	SET
		NULL,
        CONSTRAINT `FK_Topic_RegistrationPeriod` FOREIGN KEY (registration_period_id) REFERENCES RegistrationPeriod(registration_period_id)ON UPDATE CASCADE ON DELETE
	SET
		NULL,
        CONSTRAINT `FK_Topic_Teacher` FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id) ON UPDATE CASCADE ON DELETE
    SET
        NULL
);




DROP TABLE IF EXISTS `GroupStudent`;
CREATE TABLE `groupstudent` (
  `group_id` char(12) NOT NULL,
  `leader_id` char(12) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_full` bit(1) DEFAULT (0),
  `current_number` int DEFAULT (1),
  `topic_id` char(10) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT (0),
  `board_id` char(12) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  KEY `FK_GroupStuden_Board` (`board_id`),
  KEY `FK_Group_Topic` (`topic_id`),
  CONSTRAINT `FK_Group_Topic` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`topic_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_GroupStuden_Board` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`) ON DELETE SET NULL ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
    student_id CHAR(12),-- Niên khóa: 2020, 2021, 2022
    school_year CHAR(10),
    person_id CHAR(12),
    major_id CHAR(12),
    group_id CHAR(12),-- trường này cho biết sinh viên đã có group hay chưa. Mặc định là null --> chưa có group
    PRIMARY KEY `PK_Student`(student_id),
    CONSTRAINT `FK_Student_Person` FOREIGN KEY (person_id) REFERENCES Person(person_id) ON UPDATE CASCADE ON DELETE
    SET
        NULL,
        CONSTRAINT `FK_Student_Major` FOREIGN KEY (major_id) REFERENCES Major(major_id) ON UPDATE CASCADE ON DELETE
    SET
        NULL,
        CONSTRAINT `FK_Student_Group` FOREIGN KEY (group_id) REFERENCES `GroupStudent`(group_id) ON DELETE
    SET
        NULL
);

DROP TABLE IF EXISTS `joingroup`;
CREATE TABLE `joingroup` (
  `group_id` char(12) NOT NULL,
  `student_id` char(12) NOT NULL,
  `status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`group_id`,`student_id`),
  UNIQUE KEY `unique` (`group_id`,`student_id`),
  KEY `FK_JoinGroup_Student` (`student_id`),
  CONSTRAINT `FK_JoinGroup_GroupStudent` FOREIGN KEY (`group_id`) REFERENCES `groupstudent` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_JoinGroup_Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
    
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `notification_id` char(12) NOT NULL,
  `notification_title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `sender_id` char(12) DEFAULT NULL,
  `receiver_id` char(12) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `FK_Notification_Person_sender` (`sender_id`),
  KEY `FK_Notification_Person_receiver` (`receiver_id`),
  CONSTRAINT `FK_Notification_Person_receiver` FOREIGN KEY (`receiver_id`) REFERENCES `person` (`person_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_Notification_Person_sender` FOREIGN KEY (`sender_id`) REFERENCES `person` (`person_id`) ON DELETE SET NULL ON UPDATE CASCADE
);   
    
-- -------------------------------------------------INSERT DATA-----------------------------------------------
INSERT INTO `Major`(major_id, major_name, is_active, `description`) VALUES ('MA10000000', N'Công nghệ phần mềm', 1, '');
INSERT INTO `Major`(major_id, major_name, is_active, `description`) VALUES ('MA10000001', N'Hệ thống thông tin', 1, '');
INSERT INTO `Major`(major_id, major_name, is_active, `description`) VALUES ('MA10000002', N'An toàn thông tin', 1, '');
INSERT INTO `Major`(major_id, major_name, is_active, `description`) VALUES ('MA10000003', N'Công nghệ thông tin', 1, '');

-- Student 
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000001', N'Phạm Nguyễn Nhựt Trường', 1, N'Tiền Giang', '0393012069', '20110756@student.hcmute.edu.vn', 'student', N'Còn học');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000002', N'Đỗ Dương Thái Tuấn', 1, N'Buôn Mê Thuột', '0393987654', '20110743@student.hcmute.edu.vn', 'student', N'Còn học');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000003', N'Vũ Hoàng Anh', 1, N'Đồng Nai', '0393123456', '20110205@student.hcmute.edu.vn', 'student', N'Còn học');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000004', N'Trần Chí Mỹ', 1, N'Bình Thuận', '0393123567', '20110202@student.hcmute.edu.vn', 'student', N'Còn học');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000005', N'Phan Văn Phước', 1, N'Vũng Tàu', '0393123560', '20110101@student.hcmute.edu.vn', 'student', N'Còn học');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000006', N'Nguyễn Tú Nhi', 0, N'Đăk Lak', '0393123567', '20110102@student.hcmute.edu.vn', 'student', N'Còn học');

-- Giảng viên
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email,  `role`, `description`)
VALUES('PE00000007', N'Huỳnh Xuân Phụng', 1, N'TP HCM', '0393zzzXXX', 'phunghx@hcmute.edu.vn', 'teacher', N'Giảng viên');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email,  `role`, `description`)
VALUES('PE00000008', N'Nguyễn Thị Thanh Vân', 0, N'TP HCM', '0393uuuXXX', 'vanntt@hcmute.edu.vn', 'teacher', N'Giảng viên');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000009', N'Nguyễn Thành Sơn', 1, N'TP HCM', '0393zzzXXX', 'sonnt@hcmute.edu.vn', 'teacher', N'Giảng viên');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000010', N'Trần Công Tú', 1, N'TP HCM', '0393zzzXXX', 'tutc@hcmute.edu.vn', 'teacher', N'Giảng viên');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000011', N'Lê Thị Minh Châu', 1, N'TP HCM', '0393zzzXXX', 'chaultm@hcmute.edu.vn', 'teacher', N'Giảng viên');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000012', N'Nguyễn Trần Thi Văn', 1, N'TP HCM', '0393zzzXXX', 'vanntt@hcmute.edu.vn', 'teacher', N'Giảng viên');

-- Admin
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000013', N'Admin1', 1, N'TP HCM', '0393aaaXXX', 'admin1@hcmute.edu.vn', 'admin', N'Admin 1');
INSERT INTO `Person`(person_id, full_name, gender, address, phonenumber, email, `role`, `description`)
VALUES('PE00000014', N'Admin2', 1, N'TP HCM', '0393bbbXXX', 'admin2@hcmute.edu.vn', 'admin', N'Admin 2');


INSERT INTO  `Teacher`(teacher_id, person_id, major_id, is_head) VALUES('TE00000001', 'PE00000007', 'MA10000000', 1);
INSERT INTO  `Teacher`(teacher_id, person_id, major_id, is_head) VALUES('TE00000002', 'PE00000008', 'MA10000001', 1);
INSERT INTO  `Teacher`(teacher_id, person_id, major_id, is_head) VALUES('TE00000003', 'PE00000009', 'MA10000002', 0);
INSERT INTO  `Teacher`(teacher_id, person_id, major_id, is_head) VALUES('TE00000004', 'PE00000010', 'MA10000003', 0);
INSERT INTO  `Teacher`(teacher_id, person_id, major_id, is_head) VALUES('TE00000005', 'PE00000011', 'MA10000002', 0);
INSERT INTO  `Teacher`(teacher_id, person_id, major_id, is_head) VALUES('TE00000006', 'PE00000012', 'MA10000002', 0);

INSERT INTO `Admin`(admin_id, person_id) VALUES('AD00000001', 'PE00000013');
INSERT INTO `Admin`(admin_id, person_id) VALUES('AD00000002', 'PE00000014');


INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000001', '20110756@student.hcmute.edu.vn', 'pnnt', N'Tài khoản sinh viên', 'PE00000001');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000002', '20110743@student.hcmute.edu.vn', 'ddtt', N'Tài khoản sinh viên', 'PE00000002');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000003', '20110205@student.hcmute.edu.vn', 'vha', N'Tài khoản sinh viên', 'PE00000003');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000004', '20110202@student.hcmute.edu.vn', 'tcm', N'Tài khoản sinh viên', 'PE00000004');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000005', '20110101@student.hcmute.edu.vn', 'pvp', N'Tài khoản sinh viên', 'PE00000005');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000006', '20110102@student.hcmute.edu.vn', 'ntn', N'Tài khoản sinh viên', 'PE00000006');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000007', 'phunghx@hcmute.edu.vn', 'hxp', N'Tài khoản giảng viên', 'PE00000007');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000008', 'vanntt@hcmute.edu.vn', 'nttv', N'Tài khoản giảng viên', 'PE00000008');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000009', 'sonnt@hcmute.edu.vn', 'nts', N'Tài khoản giảng viên', 'PE00000009');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000010', 'tutc@hcmute.edu.vn', 'tct', N'Tài khoản giảng viên', 'PE00000010');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000011', 'chaultm@hcmute.edu.vn', 'ltmc', N'Tài khoản giảng viên', 'PE00000011');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000012', 'vannguyentt@hcmute.edu.vn', 'nttv', N'Tài khoản giảng viên', 'PE00000012');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000013', 'admin1@hcmute.edu.vn', 'admin1', N'Tài khoản admin', 'PE00000013');
INSERT INTO `Account`(account_id, username, `password`, `description`, person_id) 
VALUES('AC00000014', 'admin2@hcmute.edu.vn', 'admin2', N'Tài khoản admin', 'PE00000014');

INSERT INTO `Board`(board_id, board_name, no_member, `description`) 
VALUES('BO00000001', 'This is board 1', 3, 'No description');
INSERT INTO `Board`(board_id, board_name, no_member, `description`) 
VALUES('BO00000002', 'This is board 2', 2, 'No description');
INSERT INTO `Board`(board_id, board_name, no_member, `description`) 
VALUES('BO00000003', 'This is board 3', 2, 'No description');
INSERT INTO `Board`(board_id, board_name, no_member, `description`) 
VALUES('BO00000004', 'This is board 2', 3, 'No description');

INSERT INTO `TeacherBoard`(teacher_id, board_id) VALUES('TE00000001', 'BO00000001');
INSERT INTO `TeacherBoard`(teacher_id, board_id) VALUES('TE00000002', 'BO00000003');
INSERT INTO `TeacherBoard`(teacher_id, board_id) VALUES('TE00000003', 'BO00000002');


INSERT INTO `RegistrationPeriod`(registration_period_id, registration_period_name, is_active, `description`, semeter, school_year, is_registration_teacher ,open_date, close_date) 
VALUES('RP00000001', N'HK1', 0, N'Đây là thời gian đăng kí đề tài', 1, 2020, 1,'2020/10/10', '2020/11/11');
INSERT INTO `RegistrationPeriod`(registration_period_id, registration_period_name, is_active, `description`, semeter, school_year, is_registration_teacher , open_date, close_date) 
VALUES('RP00000002', N'HK2', 0, N'Đây là thời gian đăng kí đề tài', 2, 2020, 0, '2020/4/10', '2020/6/11');
INSERT INTO `RegistrationPeriod`(registration_period_id, registration_period_name, is_active, `description`, semeter, school_year, is_registration_teacher , open_date, close_date) 
VALUES('RP00000003', N'HK1', 0, N'Đây là thời gian đăng kí đề tài', 1, 2021, 0, '2021/11/10', '2021/12/11');
INSERT INTO `RegistrationPeriod`(registration_period_id, registration_period_name, is_active, `description`, semeter, school_year, is_registration_teacher , open_date, close_date) 
VALUES('RP00000004', N'HK1', 1, N'Đây là thời gian đăng kí đề tài', 1,2022, 0, '2022/10/10', '2021/11/1');
INSERT INTO `RegistrationPeriod`(registration_period_id, registration_period_name, is_active, `description`, semeter, school_year, is_registration_teacher , open_date, close_date) 
VALUES('RP00000005', N'HK2', 1, N'Đây là thời gian đăng kí đề tài', 2, 2022, 1, '2022/11/11', '2022/12/30');

INSERT INTO `Topic`(topic_id, topic_name, `status`, `description`, is_selected, is_full, max_mo_member, major_id, teacher_id, registration_period_id) 
VALUES ('TO00000001', 'Phần mềm hack NASA', 0, 'Đề tài ngành công nghệ phần mềm', 0, 0, 3, 'MA10000000', 'TE00000001', 'RP00000004');
INSERT INTO `Topic`(topic_id, topic_name, `status`, `description`, is_selected, is_full, max_mo_member, major_id, teacher_id, registration_period_id) 
VALUES ('TO00000002', 'Phần mềm chống hack NASA', 1, 'Đề tài ngành an toàn thông tin', 0, 0, 3, 'MA10000002', 'TE00000002', 'RP00000004');
INSERT INTO `Topic`(topic_id, topic_name, `status`, `description`, is_selected, is_full, max_mo_member, major_id, teacher_id, registration_period_id) 
VALUES ('TO00000003', 'Hệ thống truyền dẫn tốc độ cao', 0, 'Đề tài ngành công nghệ phần mềm', 1, 0, 3, 'MA10000001', 'TE00000003', 'RP00000005');

INSERT INTO `GroupStudent`(group_id, `description`, is_full, topic_id) 
VALUES('GS00000001', 'Group 1', 0, 'TO00000001');
INSERT INTO `GroupStudent`(group_id,  `description`, is_full, topic_id) 
VALUES('GS00000002', 'Group 1', 1, 'TO00000002');
INSERT INTO `GroupStudent`(group_id, `description`, is_full, topic_id) 
VALUES('GS00000003', 'Group 1', 0, 'TO00000001');
INSERT INTO `GroupStudent`(group_id, `description`, is_full, topic_id) 
VALUES('GS00000004', 'Group 1', 1, 'TO00000003');

INSERT INTO `Student`(student_id, school_year, person_id, major_id, group_id) 
VALUES('ST00000001', '2020-2024', 'PE00000001', 'MA10000003', null);
INSERT INTO `Student`(student_id, school_year, person_id, major_id, group_id) 
VALUES('ST00000002', '2020-2024', 'PE00000002', 'MA10000003', 'GS00000001');
INSERT INTO `Student`(student_id, school_year, person_id, major_id, group_id) 
VALUES('ST00000003', '2020-2024', 'PE00000003', 'MA10000003', 'GS00000001');
