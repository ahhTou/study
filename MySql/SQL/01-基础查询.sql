#进阶1：基础查询
#查询列表可以是 表中的字段、常量值、表达式、函数
#查询的结果是一个虚拟的表格

USE sakila;

# 1.查询单个 字段
SELECT last_name FROM employees;

# 2.查询多个 字段
SELECT last_name, salary,email FROM employees;

# 3.查询表中的 所有字段
SELECT * FROM employees;

# 4.查询常量值
SELECT 'john';

# 5.查询表达式
SELECT 100%98;

# 6.查询函数
SELECT VERSION();

# 7.起别名
# 方式1
SELECT 100%98 AS 结果;
SELECT last_name AS 性别, first_name AS 名字 FROM employees;
# 方式2
SELECT last_name 性别, first_name 名字 FROM employees;

# 案例 查询salary，显示结果为output
SELECT	salary AS "out put" FROM employees;

# 8.去重 DISTINCT
# 案例去重查询员工表设计到的所有部门编号
SELECT DISTINCT department_id FROM employees;

# 9.+ 号的作用 
# 两个操作数都为数值型做加法运算
# 有字符型，将字符型转为数值型，转换失败将+号为字符串
# 案例： 查询员工名 和 性 链接成一个字段，并显示为 姓名
SELECT 
  last_name + first_name AS 姓名 
FROM
  employees ;

	
# 成功案例	
SELECT 
	CONCAT (last_name , first_name) AS 姓名 
FROM
	employees;
	
#查看表结构
DESC departments;

#显示出表employees的全部列，各个列之间用逗号连接

USE sakila;

#test
SELECT 
	IFNULL(commission_pct,0) AS 奖金率,
	commission_pct
FROM
	employees;
	

SELECT 
  CONCAT(
    `first_name`,
    ',',
    `last_name`,
    ',',
    IFNULL(commission_pct, 0)
  ) AS out_put 
FROM
employees;