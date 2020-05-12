# 常见函数
/*
概念:类似于java的方法，将一组逻辑语句封装在方法体中，对外暴露方法名
好处: 1、隐藏了实现细节2、 提高代码的重用性
调用: select 函数名(实参列表)[ from 表] ;
特点:
	①叫什么(函数名)
	②千什么( 函数功能)
分类:
	1、单行函数
	如concat、 length、 ifnull等
	2、分组函数
	功能:做统计使用，又称为统计函数、聚合函数、组函数
	
	# 1.单行函数，字符函数
	# 2.数学函数
	# 3.日期函数
	# 4.其他函数【补充】
	# 5.流程控制函数【补充】


*/
# 1.单行函数，字符函数

# 1.length
SELECT LENGTH('john');
SELECT LENGTH('张三丰hahaha');

# 2.concat
SELECT CONCAT(last_name, '_', first_name) AS 姓名 FROM employees;

# 3.upper lower
SELECT UPPER('john');
SELECT LOWER('joHn');
SELECT CONCAT(UPPER(last_name),'_',LOWER(first_name)) AS 姓名 FROM employees;

# 4.substr, substring 截取 索引从1开始

SELECT SUBSTR('ahhTou', 3);
SELECT SUBSTR('ahhTou' FROM 3);
SELECT SUBSTR('ahhTou',3,1);
SELECT SUBSTR('ahhTou' FROM 3 FOR 1);

#案例：姓名中首字符大写，其他字符小写，然后用_拼接，显示出来
SELECT 
  CONCAT(
    UPPER(SUBSTR(last_name, 1, 1)),
    '_',
    LOWER(SUBSTR(last_name, 2))
  )  AS out_put
 FROM employees;
 
# 5.instr 返回子串第一次出现的索引
SELECT INSTR('ahhTou', 'Tou') AS out_put;

# 6.trim
SELECT TRIM('     ahhTou      ') AS out_put;

SELECT TRIM('x' FROM 'xxxxxxxxxxahhTouxxxxxxxxx') AS out_put;

# 7.lpad rpad 左填充，超出截断
SELECT LPAD('ahhTou',10,'*') AS output;
SELECT RPAD('ahhTou',10,'*') AS output;

# 8.replace 替换
SELECT REPLACE('ProahhTouPro','Pro','Pro你妈呢，傻狗') AS out_put;


SHOW VARIABLES LIKE '%char%' # 2.数学函数
# 3.日期函数
# 4.其他函数【补充】
# 5.流程控制函数【补充】
 