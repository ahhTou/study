 -- ==============事务 ================
 SET autocommit = 0 ; -- 关闭
 SET autocommit = 1 ; -- 开启（默认）
 
 -- 手动处理事务
  SET autocommit = 0 ; 
 -- 事务开启
START TRANSACTION -- 标记一个事务的开始 

INSERT xx
INSERT xx

-- 提交 ： 持久化 （成功！）
COMMIT ;
 
 -- 回滚 ： 回到原来的样子 （失败！）
ROLLBACK;

-- 事务结束
 SET autocommit = 1 ; 
 
 -- 了解
 SAVEPOINT; 保存点 -- 设置一个事务的保存点 
 ROLLBACK TO SAVEPOINT; 保存点 -- 回滚到保存点
 RELEASE SAVEPOINT ;保存点  -- 删除保留点
 
 CREATE DATABASE shop CHARACTER SET utf8 COLLATE utf8_general_ci;
 
 USE shop;
 
 CREATE TABLE `account`(
	`id` INT(3) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(30) NOT NULL,
	`money` DECIMAL(9,2) NOT NULL,
	PRIMARY KEY (`id`)
 )ENGINE=INNODB DEFAULT CHARSET = utf8;
 
 INSERT INTO account (`name`,`money`)
 VALUES ('A', 2000.00),('b',10000);
 
 
 -- 模拟事务
 SET autocommit = 0 ; -- 关闭自动提交
 START TRANSACTION ; -- 开启一个事务
 UPDATE account SET money=money-500 WHERE `name` = 'A' ;-- a减500
 UPDATE account SET money=money-500 WHERE `name` = 'b' ;--  b减500
 COMMIT;
 ROLLBACK;
 SET autocommit = 1;
 
 -- =================== 索引 =====================
 -- 1、在创建的时候给字段增加索引
 -- 2、创建完成后，增加索引
 
 -- 显示所有的的索引信息
 SHOW INDEX FROM `student`;
 
 -- 增加一个索引 									(索引名) 列明
 ALTER TABLE school.`student` ADD FULLTEXT INDEX `name` (`name`);
 
 -- explain 分析sql执行的情况
 EXPLAIN SELECT * FROM student ; -- 非全文索引
 
EXPLAIN SELECT * FROM student WHERE MATCH(`name`) AGAINST('刘') ;
 
 -- 插入100万条数据
 /**
 delimiter $$ -- 写函数之前要写的标志
 create function mock_data()
 return int 
 begin 
	declare num int default 1000000;
	while i<num do

		-- 插入语句
		insert into app_user(`name`,`email`,`sex`,`phone`,`gender`,`password`,`age`)
		values(
			concat('用户', i),
			'23432432@qq.com',
			concat()
		)
	
	end while ;
	
**/
	
SELECT * FROM `student` WHERE `name` = '用户9999'; -- 4.276 sec
SELECT * FROM `student` WHERE `name` = '用户9999';  -- 4.284 sec

CREATE INDEX id_student_user_name ON `student`(`name`);

SELECT * FROM `student` WHERE `name` = '用户9999';  --  0 sec
EXPLAIN SELECT * FROM `student` WHERE `name` = '用户9999';  --  0 sec


-- ============== 用户权限=========

-- 创建用户
CREATE USER test IDENTIFIED BY '123456';

-- 修改密码 （当前）
SET PASSWORD = PASSWORD('123456');

--  修改密码 （指定）
SET PASSWORD FOR test = PASSWORD('123456');


-- 重命名
RENAME USER test TO test2;


-- 用户授权  除了给别人授权，其他都能干
GRANT ALL PRIVILEGES ON *.*  TO test2 ;-- 所有权限

-- 查询权限
SHOW GRANTS FOR test2; 
SHOW GRANTS FOR root@localhost;
 

-- 撤销权限 revoke 哪些权限，在哪个库，给谁撤销
REVOKE ALL PRIVILEGES ON *.* FROM test2;

-- 删除用户
DROP USER test2;