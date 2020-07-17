# 分组函数
/*
功能：用作统计使用，又称为聚合函数或者
统计函数或组函数

分类:
sum求和、avg平均值、max最大值、min最小值
、count计算个数

特点:
1、sum.avg--般用于处理数值型
max、min. count可 以处理任何类型
2、以上函数都忽略null值

3、 可以和distinct搭配实现去重的运算

4、count函数的单独介绍
般使用count(*)用作统计行数

5、和分组函数一同查询的字段要求是group by后的字段



*/

# 1.简单的使用
SELECT SUM(salary) FROM employees;
SELECT AVG(salary) FROM employees;
SELECT MIN(salary) FROM employees;
SELECT MAX(salary) FROM employees;
SELECT COUNT(salary) FROM employees;

SELECT SUM(salary) AS 和,
AVG(salary) AS 平均值
FROM employees;

# 2.参数支持哪些内容
SELECT SUM(last_name),AVG(last_name)
FROM employees;
SELECT SUM(hiredate),AVG(hiredate)
FROM employees;
SELECT MAX(last_name),MIN(last_name)
FROM employees;
SELECT MAX(hiredate),MIN(hiredate)
FROM employees;
SELECT COUNT(hiredate),COUNT(hiredate)
FROM employees;

# 3.和distinct搭配
SELECT COUNT(DISTINCT salary), COUNT(salary)
FROM employees;

# 4.count函数的详细介绍
SELECT COUNT(salary) FROM employees;

SELECT COUNT(*) FROM employees;

#每一列添加一个1 来统计多少个1（统计多少列）
SELECT COUNT(1) FROM employees; 

# 效率
# MYISAM 的存储引擎下，count(*) 的效率高
# INNOB 的存储引擎下 ，count(*)和count(字段)效率差不多，前者更高一点

# 6.和分组函数一同查询的字段有限制
SELECT AVG(salary),employees_id FROM employees;
