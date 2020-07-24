CREATE DATABASE IF NOT EXISTS newdb;


DROP DATABASE IF EXISTS newdb;

USE study_school;

SHOW DATABASES;

-- 字符串使用 单引号括起来
CREATE TABLE IF NOT EXISTS `student`(
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	`name` VARCHAR(30) NOT NULL DEFAULT '匿名' COMMENT '姓名',
	`pwd` VARCHAR(20) NOT NULL DEFAULT '123456' COMMENT '密码',
	`sex` VARCHAR(2) NOT NULL DEFAULT '男' COMMENT '性别',
	`brithday` DATETIME DEFAULT NULL COMMENT '出生日期',
	`address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
	`email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY(`id`)	
)ENGINE = INNODB DEFAULT CHARSET=utf8;


SHOW CREATE DATABASE study_school;

SHOW CREATE TABLE student;

ALTER TABLE `student` RENAME AS `studentPro`;

ALTER TABLE `studentpro` RENAME AS `student`;

CREATE TABLE IF NOT EXISTS `teacher`(
	`id` INT(4) NOT NULL AUTO_INCREMENT COMMENT '学号',
	PRIMARY KEY(`id`)	
)ENGINE = INNODB DEFAULT CHARSET=utf8;

ALTER TABLE teacher ADD age INT(11);

ALTER TABLE teacher MODIFY age VARCHAR(11);
ALTER TABLE teacher CHANGE age age1 INT(1);

ALTER TABLE teacher DROP age1;


INSERT INTO `teacher`(`id`) VALUES (3),(4),(5);

INSERT INTO `student`
VALUES (5,'ahhTou','123456','男','2020-07-10',1,1);

UPDATE `student` SET `name`='ahhTouUp' WHERE id=1;

UPDATE `student` SET `name`='ahhTou', `email`='123456@qq.com' , brithday=CURRENT_TIME WHERE id = 1 AND sex='男';


DELETE FROM `student` WHERE id=1;

TRUNCATE `teacher`;

CREATE DATABASE IF NOT EXISTS `school`;
-- 创建一个school数据库
USE `school`;-- 创建学生表
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
`studentno` INT(4) NOT NULL COMMENT '学号',
`loginpwd` VARCHAR(20) DEFAULT NULL,
`studentname` VARCHAR(20) DEFAULT NULL COMMENT '学生姓名',
`sex` TINYINT(1) DEFAULT NULL COMMENT '性别，0或1',
`gradeid` INT(11) DEFAULT NULL COMMENT '年级编号',
`phone` VARCHAR(50) NOT NULL COMMENT '联系电话，允许为空',
`address` VARCHAR(255) NOT NULL COMMENT '地址，允许为空',
`borndate` DATETIME DEFAULT NULL COMMENT '出生时间',
`email` VARCHAR (50) NOT NULL COMMENT '邮箱账号允许为空',
`identitycard` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
PRIMARY KEY (`studentno`),
UNIQUE KEY `identitycard`(`identitycard`),
KEY `email` (`email`)
)ENGINE=MYISAM DEFAULT CHARSET=utf8;


-- 创建年级表
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`(
	`gradeid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '年级编号',
  `gradename` VARCHAR(50) NOT NULL COMMENT '年级名称',
    PRIMARY KEY (`gradeid`)
) ENGINE=INNODB AUTO_INCREMENT = 6 DEFAULT CHARSET = utf8;

-- 创建科目表
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`(
	`subjectno`INT(11) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
    `subjectname` VARCHAR(50) DEFAULT NULL COMMENT '课程名称',
    `classhour` INT(4) DEFAULT NULL COMMENT '学时',
    `gradeid` INT(4) DEFAULT NULL COMMENT '年级编号',
    PRIMARY KEY (`subjectno`)
)ENGINE = INNODB AUTO_INCREMENT = 19 DEFAULT CHARSET = utf8;

-- 创建成绩表
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`(
	`studentno` INT(4) NOT NULL COMMENT '学号',
    `subjectno` INT(4) NOT NULL COMMENT '课程编号',
    `examdate` DATETIME NOT NULL COMMENT '考试日期',
    `studentresult` INT (4) NOT NULL COMMENT '考试成绩',
    KEY `subjectno` (`subjectno`)
)ENGINE = INNODB DEFAULT CHARSET = utf8;


-- 插入学生数据 其余自行添加 这里只添加了2行
INSERT INTO `student` (`studentno`,`loginpwd`,`studentname`,`sex`,`gradeid`,`phone`,`address`,`borndate`,`email`,`identitycard`)
VALUES
(1000,'123456','张伟',0,2,'13800001234','北京朝阳','1980-1-1','text123@qq.com','123456198001011234'),
(1001,'123456','赵强',1,3,'13800002222','广东深圳','1990-1-1','text111@qq.com','123456199001011233');

-- 插入成绩数据  这里仅插入了一组，其余自行添加
INSERT INTO `result`(`studentno`,`subjectno`,`examdate`,`studentresult`)
VALUES
(1000,1,'2013-11-11 16:00:00',85),
(1000,2,'2013-11-12 16:00:00',70),
(1000,3,'2013-11-11 09:00:00',68),
(1000,4,'2013-11-13 16:00:00',98),
(1000,5,'2013-11-14 16:00:00',58);

-- 插入年级数据
INSERT INTO `grade` (`gradeid`,`gradename`) VALUES(1,'大一'),(2,'大二'),(3,'大三'),(4,'大四'),(5,'预科班');

-- 插入科目数据
INSERT INTO `subject`(`subjectno`,`subjectname`,`classhour`,`gradeid`)VALUES
(1,'高等数学-1',110,1),
(2,'高等数学-2',110,2),
(3,'高等数学-3',100,3),
(4,'高等数学-4',130,4),
(5,'C语言-1',110,1),
(6,'C语言-2',110,2),
(7,'C语言-3',100,3),
(8,'C语言-4',130,4),
(9,'Java程序设计-1',110,1),
(10,'Java程序设计-2',110,2),
(11,'Java程序设计-3',100,3),
(12,'Java程序设计-4',130,4),
(13,'数据库结构-1',110,1),
(14,'数据库结构-2',110,2),
(15,'数据库结构-3',100,3),
(16,'数据库结构-4',130,4),
(17,'C#基础',130,1);


`student`

-- 插入100万数据.
DELIMITER $$
-- 写函数之前必须要写，标志
SET GLOBAL log_bin_trust_function_creators=TRUE;
CREATE FUNCTION mock_data()
RETURNS INT
BEGIN
DECLARE num INT DEFAULT 1000000;
DECLARE i INT DEFAULT 0;
WHILE i<num DO
INSERT INTO `student`(`name`,`email`,`phone`,`sex`)VALUES(CONCAT('用户',i),'19224305@qq.com','123456789',FLOOR(RAND()*2));
SET i=i+1;
END WHILE;
RETURN i DETERMINISTIC;
END;

SELECT mock_data(); -- 执行此函数 生成一百万条数据


DROP FUNCTION IF EXISTS mock_data;
-- 写函数之前必须要写，标志:$$
DELIMITER $$
CREATE FUNCTION mock_data()
RETURNS INT
-- 注意returns，否则报错。
BEGIN
DECLARE num INT DEFAULT 1000000;
-- num 作为截止数字，定义为百万，
DECLARE i INT DEFAULT 0;
WHILE i < num DO
INSERT INTO `student`(`name`,`email`,`phone`,`sex`)VALUES(CONCAT('用户',i),'19224305@qq.com','123456789',FLOOR(RAND()*2));
SET i = i + 1;
END WHILE;
RETURN i;
END;

-- 查询全部的学生
SELECT * FROM student;

-- 查询指定字段
SELECT `studentno`, `name` FROM student;

-- 使用别名
SELECT `studentno` AS 学号, `name` AS 学生姓名 FROM student;
SELECT `studentno` 学号, `name`  学生姓名 FROM student;

-- 函数 concat(a, b)
SELECT CONCAT('姓名：',`name`)  AS 新名字 FROM student;


-- 去重一下有哪些同学参加了考试
SELECT * FROM result;  -- 查询全部的成绩

SELECT DISTINCT `studentno` FROM result; -- 查询有那些同学参加了考试


SELECT VERSION();
