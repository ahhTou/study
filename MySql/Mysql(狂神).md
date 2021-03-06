BV1NJ411J79W

# 基本命令

##### 连接和退出

```bash
mysql -uroot -p123

exit;
```

##### 基础操作

```mysql
show databases; -- 查看

use school; -- 切换数据库

show tables; -- 参看数据库中所有的表

describe student -- 显示数据库中所有表的信息

-- 单行注释
/**
多行注释
**/
```

##### 创建数据库

```mysql 
create database westos;
```

# 数据库

## 操作数据库

##### 创建数据库

```mysql
create database [if not exists ]newdb;
```

##### 删除数据库

```mysql
drop database [if exists] newdb;
```

##### 使用数据库

```mysql
USE study_school;
USE `study_school`; -- 如果字段里面有特殊字符就要这些写
```

##### 查看数据库

```mysql
SHOW DATABASES;
```

## 数据库的基本类型

##### 数值

| 数据类型名称 | 描述                 | 大小               |
| ------------ | -------------------- | ------------------ |
| tinyint      | 十分小的数据         | 1个字节            |
| smallint     | 较小的数据           | 2个字节            |
| mediumint    | 中等大小             | 3个字节            |
| **int**      | **标准的整数**       | **4个字节**        |
| bigint       | 较大的数据           | 8个字节            |
| float        | 浮点数               | 4个字节            |
| double       | 浮点数               | 8个字节            |
| decimal      | 字符串行形式的浮点数 | 金融计算的时候使用 |

##### 字符串

| 名称        | 描述               | 大小        |
| ----------- | ------------------ | ----------- |
| char        | 字符串固定的大小的 | 0~255       |
| **varchar** | **可变字符串**     | **0~65535** |
| tinytext    | 微型文本           | 2^8 -1      |
| text        | 文本串             | 2^16 -1     |

##### 时间日期

| 名称          | 格式                       | 描述                 |
| ------------- | -------------------------- | -------------------- |
| data          | YYYY-MM-DD                 | 日期                 |
| time          | HH:mm:ss                   | 时间                 |
| **datetime**  | **YYYY-MM-DD HH:mm:ss**    | **完整时间**，最常用 |
| **timestamp** | **1970.1.1到现在的毫秒数** | **常用**             |
| year          | 年份表示                   |                      |

##### null

- 没有值，位置
- 不要用null进行运算，结果为null

## 数据的字段属性

##### Unsigned

- 无符号的整数
- 不能声明为负数

##### zerofill

- 默认使用0填充
- 不足的位数，使用0填充

##### 自增

- 通常理解为自增，自动在上一条数据的基础 + 1(默认)
- 通常用来设计唯一的主键 index, 必须是整数类型
- 可以自定义设计主键自增的起始值和步长

##### 非空

- NULL not null
- 设置后，不为null，会报错

##### 默认

- 设置默认的值

# 表

## 表_相关知识

- innodb 默认使用
- myisam

|            | MYISAM | INNODB              |
| ---------- | ------ | ------------------- |
| 事务支持   | 不支持 | 支持                |
| 数据行锁定 | 不支持 | 支持                |
| 外键约束   | 不支持 | 支持                |
| 全文索引   | 支持   | 不支持              |
| 表空间大小 | 较小   | 较大,约为两倍的大小 |

常规的使用操作

- myisam 节约空间,速度加快
- innodb 安全性较高,事务的处理,多表多用户操作



###### 在物理空间存在的位置

所有的数据库文件都存在data目录下

本质是文件存储

Mysql引擎在屋里文件上的区别

- InnoDB 在数据库中只有一个*.frm文件,以及上级目录下的ibdata1文件
- myisam 对应文件
  - *.from  - 表结构的定义文件
  - *.myd 数据文件(data)
  - *.myi 索引文件

## 表_基本操作

##### 创建

```mysql
-- 字符串使用 单引号括起来
CREATE TABLE IF NOT EXISTS `student`(
	`id` 		INT(4) 		NOT NULL AUTO_INCREMENT 	COMMENT '学号',
	`name`	 	VARCHAR(30) NOT NULL DEFAULT '匿名' 	   COMMENT '姓名',
	`pwd` 		VARCHAR(20) NOT NULL DEFAULT '123456' 	COMMENT '密码',
	`sex` 		VARCHAR(2) 	NOT NULL DEFAULT '男' 	   COMMENT '性别',
	`brithday` 	DATETIME 		     DEFAULT NULL 		COMMENT '出生日期',
	`address` 	VARCHAR(100)         DEFAULT NULL 		COMMENT '家庭住址',
	`email` 	VARCHAR(50)          DEFAULT NULL 		COMMENT '邮箱',
	PRIMARY KEY(`id`)	
)ENGINE = INNODB DEFAULT CHARSET=utf8;
```

###### 格式

```mysql
create table [if not exists] `表明`(
	'字段名' 列类型 [属性] [索引] [索引],
	'字段名' 列类型 [属性] [索引] [索引],
    ......
    '字段名' 列类型 [属性] [索引] [索引]  
)[表类型][字符集设置][注释]
```

##### 参看创建表的语句

```MYSQL
show create database study_school; -- 全部

show create table student;  -- 一个表
```

##### 查看表的结构

```mysql
desc student;
```

##### 设置数据表的字符集编码

```mysql
CHARSET=utf8;
```

不设置的话,会是默认的MySQL默认的字符集编码(latin1不支持中文)

在my.ini中配置默认的编码

```mysql
character-set-server=utf8
```

##### 修改

```MYSQL
-- 修改表名
ALTER TABLE `student` RENAME AS `studentPro`; 

-- 添加表的字段 Alter table add 字段名 列属性
alter table teacher add age int(11);

-- 修改表的字段(重命名,修改约束)
alter table teacher modify age varchar(11); -- 修改约束
alter table teacher change age age1 int(1); -- 重命名
```

##### 删除

> 所有的创建和删除操作尽量加上判断,以免报错~

```-- 删除表的字段
ALTER TABLE [if exists] teacherDROP age1;
```



## 外键

##### 方式1

创建表的时候增加约束，比较麻烦、复杂

删除有外键关系的表的时候，必须要先删除引用别人的表（从表），再删除被引用的表（主键）

##### 方式2

创建表成功后，再添加约束

# 符号和函数

## 操作符

操作符会返回布尔值

| 操作符              | 含义         | 范围        | 结果  |
| ------------------- | ------------ | ----------- | ----- |
| =                   | 等于         | 5=6         | false |
| <> 或!=             | 不等于       | 5<>6        | true  |
| <、>、>=、<=        |              |             |       |
| between ... and ... | 在某个范围内 | [2,5]       |       |
| and                 | &&           | 5>1 and 1>2 | false |
| or                  | \|\|         | 5>1 or 1>2  | true  |

## 常用函数

```mysql
-- 计算
select abs(-8); -- 绝对值
select ceiling(9.4); -- 向上取整
select floor(9.4);  -- 向下取整
select rand(); -- 返回0-1的随机数
select sign(-10); -- 判断一个数的符号

 -- 字符串函数
 select char_length('我是ahhTou'); -- 字符串长度
 select concat('我爱','二刺螈') ; -- 拼接字符串
 select insert ('我爱编程helloworld',1,2,'超级热爱' ); -- 替换字符串
 select lower('HelloWorld'); -- 小写
 select upper('helloworld'); -- 大写
 select instr('ahhTou','h'); -- 返回第一次出现字符串的索引
 select replace('ahhTou是个啥b','啥b','sb'); -- 替换字符串
 select substr('ahhTou是个傻逼',7,4); -- 返回指定字符串
 select reverse('清晨我上马'); -- 反转
 
 -- 查询姓周的同学，姓名替换为邹
 select replace(`name`,'周','邹') from student 
 where `name` like '周%';
 
 -- 时间和日期函数（记住）
 select current_date(); -- 获得当前日期;
 select curdate() ;-- 获取当前日期
 select now(); -- 获取当前的时间
 select localtime() ;-- 本地时间
select sysdate(); -- 系统时间

select year(now());
select month(now());
select day(now());
select hour(now());
select minute(now());
select second(now());

-- 系统
select system_user();
select user();
select version();
```

## md5加密

```mysql
-- 明文密码
INSERT INTO testmd5 
VALUES(1,'zhangshan','123456'),
(2,'lisi','123456'),
(3,'wangwu','123456');

-- 加密
UPDATE testmd5 SET pwd=MD5(pwd) WHERE id !=1 ;

INSERT INTO testmd5 
VALUES(4,'zhangshan',MD5('123456'));
```



## 聚合函数

| 函数名称 | 描述   |
| -------- | ------ |
| count()  | 计数   |
| sum()    | 求和   |
| avg()    | 平均值 |
| max()    | 最大值 |
| min()    | 最小值 |
| ...      | ...    |

```mysql
select count(name) from student; -- count(字段) 会忽略所有的null值
select count(*) from student; -- 不会忽略null值 本质计算行数
select count(1) from result; -- 不会忽略的所有的null值 本质计算行数

select sum(`studentresult`) as 总和 from result;
SELECT avg(`studentresult`) AS 平均数 FROM result;
SELECT max(`studentresult`) AS 最高分 FROM result;
SELECT min(`studentresult`) AS 最低分 FROM result;
```

# (重点) DML整改删

## 插入

```mysql
INSERT INTO `teacher`(`id`) VALUES (1); -- 插入单挑
INSERT INTO `teacher`(`id`) VALUES (3),(4),(5); -- 插入多条

-- 插入多个数据
insert into `student`(`name`,`pwd`,`sex`) 
values ('ahhTou','123456','男');

-- 插入多条数据 多个值
insert into `student`(`name`,`pwd`,`sex`) 
values ('ahhTou','123456','男'), ('ahhTouPro','123456','男') , ('ahhTouProMax','123456','男');

-- 省略插入值，需要全部都一一对应
INSERT INTO `student`
VALUES (5,'ahhTou','123456','男','2020-07-10',1,1);
```

## 修改

```mysql
-- 语法
-- update 表名 set colnum_name=value[,colnoum_name=value,...] where 条件

UPDATE `student` SET `name`='ahhTouUp' WHERE id=1; -- 根据条件修改

UPDATE `student` SET `name`='ahhTou' -- 不指定条件

-- 修改多个值
UPDATE `student` SET `name`='ahhTou', `email`='123@qq.com' WHERE id = 1;

-- 通过多个条件
UPDATE `student` SET `name`='ahhTou', `email`='123456@qq.com' WHERE id = 1 AND sex='男';
```

value可以是值，也可以是变量

如

```mysql
UPDATE `student` 
SET brithday=CURRENT_TIME 
WHERE id = 1 AND sex='男';
```

## 删除

> delete from 表名 [where 条件]

```mysql
-- 删除数据 （这样写会全部删除）
delete from `student`;

-- 删除指定数据
delete from `student` where id=1;
```

> truncate 命令

作用： 完全清空一个数据库表，表的结构和索引约束不会变

> 区别

- 相同点:都能删除数据，都不会删除表结构
- 不同
    - truncate 重新设置自增列计数器会归零
    - truncate 不会影响事务

# (重点) DQL查询

## 基础

##### 基础查询和别名

> select 字段 ... from 表

```mysql
-- 查询全部的学生
SELECT * FROM student;

-- 查询指定字段
SELECT `studentno`, `name` FROM student;

-- 使用别名
SELECT `studentno` AS 学号, `name` AS 学生姓名 FROM student;
SELECT `studentno` 学号, `name`  学生姓名 FROM student;

-- 函数 concat(a, b)
SELECT CONCAT('姓名：',`name`)  AS 新名字 FROM student;
```

##### 去重 distinct

```mysql
-- 去重一下有哪些同学参加了考试
SELECT * FROM result;  -- 查询全部的成绩

SELECT DISTINCT `studentno` FROM result; -- 查询有那些同学参加了考试
```

##### 数据库的列(表达式)

> 数据库中的表达式:文本值， 列，Null, 函数,计算表达式，系统变量...

```mysql
-- 查询系统版本
SELECT VERSION();

-- 用来计算
SELECT 100*3-1 AS  计算结果;

-- 查询自增的步长
SELECT @@auto_increment_increment; -- 查询步长

-- 学员成绩加一分查看
SELECT `studentno` , `studentresult` + 1 AS '提高分数' FROM result;
```

## where 条件子句

##### 逻辑运算符

> 尽量使用英文字母

| 运算符  | 语法                 | 描述   |
| ------- | -------------------- | ------ |
| and &&  | a and b     a && b   | 逻辑与 |
| or \|\| | a or b        a\|\|b | 逻辑否 |
| Not !   | not a          ! a   | 逻辑非 |

```mysql
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
```

##### 模糊查询 比较运算符

| 运算符      | 语法               | 描述                                            |
| ----------- | ------------------ | ----------------------------------------------- |
| is null     | a is null          | 如果操作符为NUII, 结果为真                      |
| is not null | a is not null      | 如果操作符不为null,结果为真                     |
| between     | a between b and c  | 若a 在b和C之间，则结果为真                      |
| **like**    | a like b           | sql匹配，如果a匹配b，则结果为真                 |
| in          | a in (a1,a2,a3...) | 假设a在a1,或者a..... 其中的某-个值中， 结果为真 |

###### like

> 模糊查询

```mysql
-- 查询 姓刘的
select `studentno`, `name` from `student`
where `name` like '刘%';

-- 查询姓刘且名字只有一个字
SELECT `studentno`, `name` FROM `student`
WHERE `name` LIKE '刘_';

-- 查询名字里带大的
SELECT `studentno`, `name` FROM `student`
WHERE `name` LIKE '%大%';
```

###### in

> 必须是具体的值

```mysql
-- 查询 1001，1002，1003 学员
SELECT `studentno`,`name` FROM `student`
WHERE `studentno` IN (1001,1002,1003);

-- 查询在北京的学生
SELECT `studentno`,`name` FROM `student`
WHERE `address` IN ('广东深圳');
```

###### null

```mysql
-- 查询地址为空的学生
SELECT `studentno`,`name` FROM `student`
WHERE `address` = '' OR `address` IS NULL;


-- 查询有出生日期的同学
SELECT `studentno`,`name` FROM `student`
WHERE `borndate` IS NOT NULL;

-- 查询没有出生日期的同学
SELECT `studentno`,`name` FROM `student`
WHERE `borndate` IS  NULL;
```

## 联表查询

> join (连接的表) on (判断条件)  连接查询
>
> where 等值查询

```mysql

--  参加了考试的同学

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




```

| 操作       | 描述                                       |
| ---------- | ------------------------------------------ |
| inner join | 如果表中至少有一个匹配，就返回行           |
| left join  | 会从左表中返回所有的值，即使右表中没有匹配 |
| right join | 会从右表中返回所有的值，即使左表中没有匹配 |

## 自连接

```mysql
CREATE TABLE `school`.`category`( `categoryid` INT(3) NOT NULL COMMENT 'id', `pid` INT(3) NOT NULL COMMENT '父id 没有父则为1', `categoryname` VARCHAR(10) NOT NULL COMMENT '种类名字', PRIMARY KEY (`categoryid`) ) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;

INSERT INTO `school`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('2', '1', '信息技术');
INSERT INTO `school`.`CATEGOrY` (`categoryid`, `pid`, `categoryname`) VALUES ('3', '1', '软件开发');
INSERT INTO `school`.`category` (`categoryid`, `PId`, `categoryname`) VALUES ('5', '1', '美术设计');
INSERT INTO `School`.`category` (`categoryid`, `pid`, `categorynamE`) VALUES ('4', '3', '数据库');
INSERT INTO `school`.`category` (`CATEgoryid`, `pid`, `categoryname`) VALUES ('8', '2', '办公信息');
INSERT INTO `school`.`category` (`categoryid`, `pid`, `CAtegoryname`) VALUES ('6', '3', 'web开发');
INSERT INTO `SCHool`.`category` (`categoryid`, `pid`, `categoryname`) VALUES ('7', '5', 'ps技术');
```

**自己的表和自己的表连接，核心: 一张表拆为两张一样的表即可**

父类

| categoryid | categoryName |
| ---------- | ------------ |
| 2          | 信息技术     |
| 3          | 软件开发     |
| 5          | 美术设计     |

子类

| pid  | categoryid | categoryName |
| ---- | ---------- | ------------ |
| 3    | 4          | 数据库       |
| 2    | 8          | 办公信息     |
| 3    | 6          | web开发      |
| 5    | 7          | Ps技术       |

操作：查询父类对应的之类关系

| 父类     | 子类     |
| -------- | -------- |
| 信息技术 | 办公信息 |
| 软件开发 | 数据库   |
| 软件开发 | web开发  |
| 美术设计 | ps技术   |
|          |          |

```mysql
SELECT a.`categoryname` AS '父栏目', b.`categoryname` AS '子栏目'
FROM `category` AS a , `category` AS b
WHERE a.`categoryid` = b.`pid`;
```

## 排序

> limit and order by

```mysql
-- 排序 : 升序ASC 降序DESC
-- ORDER BY 通过那个字段排序，怎么排
-- 查询的结果根据成绩降序 排序
select s.`studentno`,`name`,`subjectname`,`studentresult`
from `student` as s
left join `result` as r
on s.`studentno` = r.`subjectno`
left join `subject` sub
on r.`subjectno` = sub.`subjectno`
order by `studentresult` desc[asc];
```

## 分页

```mysql
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
limit 0,10
;
```

## 子查询

```mysql
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
```

## 分组过滤

```mysql
-- 查询不同课程的平均分，最高分，最低分
-- 核心:(根据不同的课程分组)
SELECT `subjectname`  AS 科目名称, AVG(`studentresult`) AS 平均分, MAX(`studentresult`) AS 最高分,MIN(`studentresult`) AS 最低分
FROM result r
INNER JOIN `subject` sub
ON r.`subjectno` = sub.`subjectno`
[where ...]
GROUP BY r.`subjectno` -- 通过字段来分组
HAVING 平均分> 80 -- 过滤
...
;
```

# 事务

## 什么是事务

> 要么都成功,要么都失败

事务原则: ACID原则 原子性，一致性，隔离性，持久性 (脏读,幻读....)

**原子性(Atomicity)**

要么都成功，要么都失败

**一致性(Consistency)**

事务前后的数据完整性要保证一致，1000

**持久性(Durability)** --- 事务提交

事务一旦提交则不可逆，被持久化到数据库中!

**隔离性(Isolation)**

事务的隔离性是多个用户并发访问数据库时,数据库为每一个用户开启的事务, 不能被其他事务的操作数据所干扰,事务之间要相互隔离。

> 隔离导致的问题

**脏读**

指一个事务读取了另外一个事务未提交的数据。

**不可重复读**

在一个事务内读取表中的某-一行数据， 多次读取结果不同。(这个不一 定是错误， 只是某些场合不对)

**虚读(幻读)**

是指在一个事务内读取到了 别的事务插入的数据，导致前后读取不一致。

## 事务流程

```mysql
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

```

## 模拟转账

```mysql
 SET autocommit = 0 ; -- 关闭自动提交
 START TRANSACTION ; -- 开启一个事务
 UPDATE account SET money=money-500 WHERE `name` = 'A' ;-- a减500
 UPDATE account SET money=money-500 WHERE `name` = 'b' ;--  b减500
 COMMIT;
 ROLLBACK;
 SET autocommit = 1;
```

# 索引

 https://blog.codinglabs.org/articles/theory-of-mysql-index.html

> MySQL官方对索引的定义为: 	**索引(Index) 是帮助MySQL高效获取数据的数据结构**。提取句子主干，就可以得到索引的本质:索引是数据结构。

## 索引的分类

> 在一个表,主键索引只能有一个,唯一索引可以有多个

- 主键索引 Primary Key
    - 唯一的标识,不可重复
- 唯一索引 Unique Key
    - 避免重复的列出现,唯一索引可以重复,多个列都可以标识位 唯一索引
- 常规索引 Key/Index
    - 默认的,index, key关键字设置
- 全文索引 FullText
    - 在特定数据库引擎下才有，Myisam
    - 快速定位数据

## 基础语法

```mysql
 -- 1、在创建的时候给字段增加索引
 -- 2、创建完成后，增加索引
 
 -- 显示所有的的索引信息
 SHOW INDEX FROM `student`;
 
 -- 增加一个索引 									(索引名) 列明
 ALTER TABLE school.`student` ADD FULLTEXT INDEX `name` (`name`);
 
 -- explain 分析sql执行的情况
 EXPLAIN SELECT * FROM student ; -- 非全文索引
 
EXPLAIN SELECT * FROM student WHERE MATCH(`name`) AGAINST('刘') ;
```

## 索引测试

> 基于100万条数据

```mysql
SELECT * FROM `student` WHERE `name` = '用户9999'; -- 4.276 sec
SELECT * FROM `student` WHERE `name` = '用户9999';  -- 4.284 sec

CREATE INDEX id_student_user_name ON `student`(`name`);

SELECT * FROM `student` WHERE `name` = '用户9999';  --  0 sec
EXPLAIN SELECT * FROM `student` WHERE `name` = '用户9999';  --  0 sec
```

## 索引原则

- 索引不是越多越好
- 不要对进程变动数据加索引
- 小数据量的表不需要加索引
- 索引一般加在常用来查询的字段上!



> 索引的数据结构

hash类型的索引

Btree : innoDB 默认的数据结构



# 权限管理和备份

## 用户管理

> sqlyog图像管理



> SQL 命令操作

```mysql
-- 创建用户
create user test identified by '123456';

-- 修改密码 （当前）
set password = password('123456');

--  修改密码 （指定）
set password for test = password('123456');


-- 重命名
rename user test to test2;


-- 用户授权  除了给别人授权，其他都能干
grant all privileges on *.*  to test2 ;-- 所有权限

-- 查询权限
show grants for test2; 
show grants for root@localhost;
 

-- 撤销权限 revoke 哪些权限，在哪个库，给谁撤销
revoke all privileges on *.* from test2;

-- 删除用户
drop user test2;
```

## 备份

为什么要备份:

- 保证重要的数据不丢失

- 数据转移

MySQL数据库备份的方式

- 直接拷贝物理文件

- 在Sqlyog这种可视化工具中手动导出

- 使用命令行导出mysqldump 命令行使用

    ```bash
    # 导出单张表 -h主机 -u用户名 -p密码数据库 表名 > 物理磁盘位置/文件名
    mysqldump -hlocalhost -uroot -p123 school student >D:/a.sql
     
    # 导出多张表  -h主机 -u用户名 -p密码数据库 表1 表2 表3 > 物理磁盘位置/文件名
    mysqldump -hlocalhost -uroot -p123 school student >D:/a.sql
      
    # 导出数据库  -h主机 -u用户名 -p密码数据库 > 物理磁盘位置/文件名
    mysqldump -hlocalhost -uroot -p123 school >D:/a.sql
     
    # 导入
    # 登录的情况下，切换到指定的数据库
    mysql> source d:/a.sql
     
    # 没有登录
    # mysql -u用户名 -p用户名 库名< 备份文件
    ```

# 规范数据库设计

## 为什么需要设计

>  当数据库比较复杂的时候，我们就需要设计了

**糟糕的数据库设计**:

- 数据冗余,浪费空间
- 数据库插入和删除都会麻烦、异常[ 屏蔽使用物理外键]
- 程序的性能差

**良好的数据库设计:**

- 节省内存空间
- 保证数据库的完整性
- 方便我们开发系统

**软件开发中，关于数据库的设计**

- 分析需求:分析业务和需要处理的数据库的需求

- 概要设计:设计关系图E-R图

## 三大范式

> 为什么要有数据库规范化

- 信息重复
- 更新异常
- 插入异常
    - 无法正常显示信息
- 删除异常

> 三大范式

**第一范式(!NF)**

原子性：保证每一列不能再分



**第二范式(2NF)**

满足第一范式

每张表只描述一件事



**第三范式(1NF)**

满足第一范式 和 第二范式

第三范式需要确保数据表中的每一列数据都和主键直接相关， 而不能间接相关。



规范性 和 性能的问题

关联查询的表不超过三张表

# JDBC

> sun 公司未来简化 开发人员的（对数据库的统一）操作，提供了一个（java操作数据的）规范，简称jdbc
>
> 这些规范的实现由具体的厂商去做
>
> 对于开发人员，我们只需要掌握jdbc接口的操作即可

## 详解

> DriverManager

```java
// 1. 加载驱动
// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
Class.forName("com.mysql.cj.jdbc.Driver"); // 固定写法，加载驱动
```

> Connection

```java
Connection connection = DriverManager.getConnection(url, username, password);

// 数据库操作都在connection里做
connection.rollback();
connection.commit();
connection.setAutoCommit(true);
```

> **Statemen**t 执行sql的对象 PrepareStatement

```java
ResultSet resultSet = statement.executeQuery(sql);
statement.executeQuery();   // 执行操作返回结果集
statement.execute();
statement.executeUpdate(); // 更新插入删除都是用这个
```

> ResultSet

```java
resultSet.getObject();
resultSet.getString();
resultSet.getInt();

resultSet.beforeFirst(); // 移动到最前面
resultSet.afterLast(); // 移动到最后面
resultSet.next(); // 移动到下一个
resultSet.previous(); // 移动到上一个
resultSet.absolute(row); // 移动到指定行
```

## 完整案例

```java
// 1. 加载驱动
Class.forName("com.mysql.cj.jdbc.Driver"); // 固定写法，加载驱动
// 2. 用户信息和url
String url = "jdbc:mysql://localhost:3306/jdbcStudy?serverTimezone=GMT%2B8";
String username = "root";
String password = "123";

// 3. 连接成功, 得到数据库对象
Connection connection = DriverManager.getConnection(url, username, password);

// 4. 执行sql对象
Statement statement = connection.createStatement();

// 5. 执行sql的对象 去 执行sql 可能会返回结果
String sql = "select * from `users`";

ResultSet resultSet = statement.executeQuery(sql);

while (resultSet.next()) {
    System.out.print("id=" + resultSet.getObject("id") + "||");
    System.out.print("id=" + resultSet.getObject("name") + "||");
    System.out.print("id=" + resultSet.getObject("password") + "||");
    System.out.print("id=" + resultSet.getObject("email") + "||");
    System.out.println("id=" + resultSet.getObject("birthday"));
}

// 6. 释放连接
resultSet.close();
statement.close();
connection.close();
```

## SQL注入

略

## PreparedStatement

PreparedStatement可以防止SQL注入。效率更好!

PreparedStatement 防SQL注入的本质，把传递进来的参数当做字符
假设其中存在转义字符，就直接忽略，会被直接转义

```java
Connection conn = null;
PreparedStatement st = null;
ResultSet rs = null;

try {
    // 获取数据库连接
    conn = JdbcUtils.getConnection();

    String sql = "INSERT INTO users(`name`, `password`,`email`,`birthday`) " +
            "VALUES(?,?,?,?)";

    // 预编译sql，先写sql，然后不执行
    st = conn.prepareStatement(sql);

    st.setString(1,"la");
    st.setString(2,"123");
    st.setString(3,"123@qq.com");
    st.setDate(4,new Date(new java.util.Date().getTime()));

    System.out.println(st);

    int i = st.executeUpdate();
    if (i > 0) {
        System.out.println("插入了" + i + "条数据");
    } else {
        System.out.println("没有插入任何数据");
    }

} catch (Exception e) {
    e.printStackTrace();
} finally {
    JdbcUtils.release(conn, st, rs);
}

```

## 事务

```java
Connection conn = null;
PreparedStatement st = null;
ResultSet rs = null;

try {

    conn = JdbcUtils.getConnection();

    // 关闭数据库自动提交，自动开启事务
    conn.setAutoCommit(false);

    String sql1 = "update `account` set money = money -100 where name ='A' ";
    String sql2 = "update `account` set money = money +100 where name ='b'";

    st = conn.prepareStatement(sql1);
    st.executeUpdate();
    st = conn.prepareStatement(sql2);
    st.executeUpdate();

    conn.commit();


} catch (Exception e) {
    e.printStackTrace();
    try {
        // 默认:失败是默认回滚
        assert conn != null;
        conn.rollback();
    } catch (Exception ee) {
        ee.printStackTrace();
    }

} finally {
    com.ahhTou.lesson02.utils.JdbcUtils.release(conn, st, rs);
}
```

## 数据库连接池

数据库连接---执行完毕--释放
连接-释放十分浪费系统资源

**池化技术:准备一些预先的资源，过来就连接预先准备好的**

常用连接数

最小连接数

最大连接数

排队等待

等待超时：100ms



编写连接池，实现一个接口

> DBCP

> C3P0

略 参看java代码



> 结论

无论使用什么数据源，本质还是一样的，DataSource接口不会变,方法就不会变