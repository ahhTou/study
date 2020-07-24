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

# 聚合函数

| 函数名称 | 描述   |
| -------- | ------ |
| count()  | 计数   |
| sum()    | 求和   |
| avg()    | 平均值 |
| max()    | 最大值 |
| min()    | 最小值 |
| ...      | ...    |

