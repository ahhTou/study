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

# 操作数据库

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

# 数据库的基本类型

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

# 数据的字段属性

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



# 表_相关知识

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

# 表_基本操作

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



# 外键_仅作了解

##### 方式1

创建表的时候增加约束，比较麻烦、复杂

删除有外键关系的表的时候，必须要先删除引用别人的表（从表），再删除被引用的表（主键）

##### 方式2

创建表成功后，再添加约束

# 操作符

操作符会返回布尔值

| 操作符              | 含义         | 范围        | 结果  |
| ------------------- | ------------ | ----------- | ----- |
| =                   | 等于         | 5=6         | false |
| <> 或!=             | 不等于       | 5<>6        | true  |
| <、>、>=、<=        |              |             |       |
| between ... and ... | 在某个范围内 | [2,5]       |       |
| and                 | &&           | 5>1 and 1>2 | false |
| or                  | \|\|         | 5>1 or 1>2  | true  |

# DML整改删

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

# DQL查询

