-- 查询系统版本
SELECT VERSION();

-- 用来计算
SELECT 100*3-1 AS  计算结果;

-- 查询自增的步长
SELECT @@auto_increment_increment; -- 查询步长

-- 学员成绩加一分查看
SELECT `studentno` , `studentresult` + 1 AS '提高分数' FROM result;

-- ================== where ========================

-- and 
SELECT `studentno`, `studentresult` FROM result
WHERE `studentresult`>=95 AND `studentresult` <=100;

-- &&
SELECT `studentno`, `studentresult` FROM result
WHERE `studentresult`>=95 && `studentresult` <=100;

-- 区间查询
SELECT `studentno`, `studentresult` FROM result
WHERE `studentresult`  BETWEEN 95 AND  `studentresult` ;

-- 除了1000号学生以外的同学的成绩
SELECT `studentno`, `studentresult` FROM result
WHERE `studentno` !=1000;

-- != not
SELECT `studentno`, `studentresult` FROM result
WHERE NOT `studentno` = 1000;

-- ===================模糊查询=====================
-- 查询姓刘的同学
-- like 的结合 %(代表0到任意个字符)  _ （一个字符）

-- 查询 姓刘的
SELECT `studentno`, `name` FROM `student`
WHERE `name` LIKE '刘%';

-- 查询姓刘且名字只有一个字
SELECT `studentno`, `name` FROM `student`
WHERE `name` LIKE '刘_';

-- 查询名字里带大的
SELECT `studentno`, `name` FROM `student`
WHERE `name` LIKE '%大%';

-- ============ in ====

-- 具体的值
-- 查询 1001，1002，1003 学员
SELECT `studentno`,`name` FROM `student`
WHERE `studentno` IN (1001,1002,1003);

-- 查询在北京的学生
SELECT `studentno`,`name` FROM `student`
WHERE `address` IN ('广东深圳');

-- ======null not null ==========
-- 查询地址为空的学生
SELECT `studentno`,`name` FROM `student`
WHERE `address` = '' OR `address` IS NULL;


-- 查询有出生日期的同学
SELECT `studentno`,`name` FROM `student`
WHERE `borndate` IS NOT NULL;

-- 查询没有出生日期的同学
SELECT `studentno`,`name` FROM `student`
WHERE `borndate` IS  NULL;



-- ================联表查询==========

--  参加了考试的同学
SELECT * FROM student;
SELECT * FROM result;

/*
	思路分析
	1.分析需求，分析查询的字段来自哪些表， (连接查询)
	
	2.确定使用哪种连接查询?7种

	确定交叉点(这两个表中哪个数据是相同的)
	
	判断的条件 ： 学生表的中 studentNo = 成绩表studentNo

*/
-- inner join
SELECT s.`studentno`,s.`name`,r.`subjectno`,r.`studentresult`
FROM student AS s 
INNER JOIN result  AS r
ON s.studentno = r.studentno;

-- right join
SELECT s.`studentno`,s.`name`,r.`subjectno`,r.`studentresult`
FROM student  s 
LEFT JOIN result   r
ON s.studentno = r.studentno;

-- left join
SELECT s.`studentno`,s.`name`,r.`subjectno`,r.`studentresult`
FROM student  s 
RIGHT JOIN result   r
ON s.studentno = r.studentno;

--  查询缺考的同学
SELECT s.`studentno`,s.`name`,r.`subjectno`,r.`studentresult`
FROM student  s 
LEFT JOIN result   r
ON s.studentno = r.studentno;


-- 多表查询
SELECT s.`studentno`, `name`,`subjectname`,`studentresult`
FROM `student` s
RIGHT JOIN `result` r
ON r.`studentno`= s.`studentno`
INNER JOIN `subject` sub
ON r.`subjectno` = sub.`subjectno`;

-- ==============  自连接 ==============
CREATE TABLE `school`.`category`( `categoryid` INT(3) NOT NULL COMMENT 'id', `pid` INT(3) NOT NULL COMMENT '父id 没有父则为1', `categoryname` VARCHAR(10) NOT NULL COMMENT '种类名字', PRIMARY KEY (`categoryid`) ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `school`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('2', '1', '信息技术');
INSERT INTO `school`.`CATEGOrY` (`categoryid`, `pid`, `categoryname`) VALUES ('3', '1', '软件开发');
INSERT INTO `school`.`category` (`categoryid`, `PId`, `categoryname`) VALUES ('5', '1', '美术设计');
INSERT INTO `School`.`category` (`categoryid`, `pid`, `categorynamE`) VALUES ('4', '3', '数据库');
INSERT INTO `school`.`category` (`CATEgoryid`, `pid`, `categoryname`) VALUES ('8', '2', '办公信息');
INSERT INTO `school`.`category` (`categoryid`, `pid`, `CAtegoryname`) VALUES ('6', '3', 'web开发');
INSERT INTO `SCHool`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('7', '5', 'ps技术');



SELECT a.`categoryname` AS '父栏目', b.`categoryname` AS '子栏目'
FROM `category` AS a , `category` AS b
WHERE a.`categoryid` = b.`pid`;


-- ================ 分页和排序 ========================

-- 排序： 升序 asc ，降序 desc

-- 查询的结果根据 成绩降序排序
SELECT s.`studentno`,`name`,`subjectname`,`studentresult`
FROM `student` AS s
LEFT JOIN `result` AS r
ON s.`studentno` = r.`subjectno`
LEFT JOIN `subject` sub
ON r.`subjectno` = sub.`subjectno`
ORDER BY `studentresult` DESC
;


-- 为什么要分页
-- 缓解数据库压力，给人的体验更好，瀑布流
-- 分页，每页只显示五秉数据
-- 语法: 	limit	 起始值，页面的大小
-- limit 0,5	1~5
-- limit 1,5	2~6  
-- 第n页	limit 0,5 (n-1)  *  pageSize, pageSize
SELECT s.`studentno`,`name`,`subjectname`,`studentresult`
FROM `student` AS s
LEFT JOIN `result` AS r
ON s.`studentno` = r.`subjectno`
LEFT JOIN `subject` sub
ON r.`subjectno` = sub.`subjectno`
LIMIT 0,10
;

-- ====================where =============
-- 查询数据库结构-1的所有考试结果（学号，科目编号，成绩），降序排序
-- 方式一 ： 连接查询
SELECT `studentno`,`subjectname`,`studentresult`
FROM `result` r
LEFT JOIN `subject` sub
ON r.`subjectno` = sub.`subjectno`
WHERE `subjectname` IS NOT NULL;

-- 方式二 子查询(由里及外)
SELECT `studentno`,`subjectname`,`studentresult`
FROM `result`
WHERE `studentno` = (
	SELECT `subjectno` FROM `subject` 
	WHERE  `subjectname` IS NOT NULL;
)
;
-- 查询所有数据库结构-1的学生学号
SELECT `subjectno` FROM `subject` WHERE  `subjectname` IS NOT NULL;

-- 分数不小于80分的学生的学号和姓名
SELECT s.`studentno`,`name`
FROM `student` s
INNER JOIN `result` r
 ON r.`studentno` = s.`studentno`
 WHERE `studentresult`>=80;

-- 在这个基础上增加一个科目，高等数学-2
SELECT s.`studentno`,`name`
FROM `student` s
INNER JOIN `result` r
 ON r.`studentno` = s.`studentno`
 WHERE `studentresult`>=80 AND `subjectno` = (
	SELECT `subjectno` FROM `subject` 
	WHERE `subjectname` = '高等数学-1'
 );
 
 
 -- 改造
SELECT `studentno`,`name` FROM `student`WHERE `studentno` IN (
 SELECT `studentno` FROM result WHERE  `studentresult`>=80 AND `subjectno` = (
	SELECT `subjectno` FROM `subject` WHERE `subjectname` = '高等数学-1'
 )
);


-- =====================常用函数====================
-- 计算
SELECT ABS(-8); -- 绝对值
SELECT CEILING(9.4); -- 向上取整
SELECT FLOOR(9.4);  -- 向下取整
SELECT RAND(); -- 返回0-1的随机数
SELECT SIGN(-10); -- 判断一个数的符号

 -- 字符串函数
 SELECT CHAR_LENGTH('我是ahhTou'); -- 字符串长度
 SELECT CONCAT('我爱','二刺螈') ; -- 拼接字符串
 SELECT INSERT ('我爱编程helloworld',1,2,'超级热爱' ); -- 替换字符串
 SELECT LOWER('HelloWorld'); -- 小写
 SELECT UPPER('helloworld'); -- 大写
 SELECT INSTR('ahhTou','h'); -- 返回第一次出现字符串的索引
 SELECT REPLACE('ahhTou是个啥b','啥b','sb'); -- 替换字符串
 SELECT SUBSTR('ahhTou是个傻逼',7,4); -- 返回指定字符串
 SELECT REVERSE('清晨我上马'); -- 反转
 
 -- 查询姓周的同学，姓名替换为邹
 SELECT REPLACE(`name`,'周','邹') FROM student 
 WHERE `name` LIKE '周%';
 
 -- 时间和日期函数（记住）
 SELECT CURRENT_DATE(); -- 获得当前日期;
 SELECT CURDATE() ;-- 获取当前日期
 SELECT NOW(); -- 获取当前的时间
 SELECT LOCALTIME() ;-- 本地时间
SELECT SYSDATE(); -- 系统时间

SELECT YEAR(NOW());
SELECT MONTH(NOW());
SELECT DAY(NOW());
SELECT HOUR(NOW());
SELECT MINUTE(NOW());
SELECT SECOND(NOW());

-- 系统
SELECT SYSTEM_USER();
SELECT USER();
SELECT VERSION();


-- ======= 聚合函数 ==========
-- 都能够统计表中的数据(想查询一个表中有多少个记录，就使用这个count () )

SELECT COUNT(NAME) FROM student; -- count(字段) 会忽略所有的null值
SELECT COUNT(*) FROM student; -- 不会忽略null值 本质计算行数
SELECT COUNT(1) FROM result; -- 不会忽略的所有的null值 本质计算行数

SELECT SUM(`studentresult`) AS 总和 FROM result;
SELECT AVG(`studentresult`) AS 平均数 FROM result;
SELECT MAX(`studentresult`) AS 最高分 FROM result;
SELECT MIN(`studentresult`) AS 最低分 FROM result;

-- 查询不同课程的平均分，最高分，最低分
-- 核心:(根据不同的课程分组)
SELECT `subjectname`  AS 科目名称, AVG(`studentresult`) AS 平均分, MAX(`studentresult`) AS 最高分,MIN(`studentresult`) AS 最低分
FROM result r
INNER JOIN `subject` sub
ON r.`subjectno` = sub.`subjectno`
GROUP BY r.`subjectno` -- 通过字段来分组
HAVING 平均分> 80 -- 过滤
;


-- ===========测试md5 加密 ==============
CREATE TABLE `testmd5` (
	`id` INT(4) NOT NULL,
	`name` VARCHAR(20) NOT NULL,
	`pwd` VARCHAR(50) NOT NULL,
	PRIMARY KEY(`id`)
) ENGINE= INNODB DEFAULT CHARSET=utf8;

-- 明文密码
INSERT INTO testmd5 
VALUES(1,'zhangshan','123456'),
(2,'lisi','123456'),
(3,'wangwu','123456');

-- 加密
UPDATE testmd5 SET pwd=MD5(pwd) WHERE id !=1 ;

INSERT INTO testmd5 
VALUES(4,'zhangshan',MD5('123456'));