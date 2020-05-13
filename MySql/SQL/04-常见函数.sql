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


SHOW VARIABLES LIKE '%char%' ;



# 2.数学函数

# 1.round 四舍五入
SELECT ROUND(1.45);
SELECT ROUND(1.453,1);

# 2.ceil 向上取整
SELECT CEIL(1.02);

# 3.floor 向下取整
SELECT CEIL(1.02);

# 4.truncate 截断
SELECT TRUNCATE(1.59999,1);

# 5.mod 取余 mod(a,b) a-a/b*b
SELECT MOD(10,-3);



# 3.日期函数
# now 返回当前系统时间 + 时间
SELECT NOW();

# curdata 返回当前系统日期，不包含时间
SELECT CURDATE();

# curtime 返回当前时间 ，不包含日期
SELECT CURTIME();

# 可以获取指定的部分，年、月、日、小时、分钟、秒
SELECT YEAR(NOW());
SELECT YEAR('1999-1-1');

SELECT YEAR(hiredate) AS 年 FROM employees;

SELECT MONTH(NOW()) 月;
SELECT MONTHNAME(NOW()) 月;


#str_to_data 讲指定的格式转化成日期

SELECT STR_TO_DATE('1998-3-2','%Y-%c-%d') AS out_put;

# 查询入职日期为1992-4-3员工日期
SELECT * FROM employees WHERE hiredate = '1992-4-3';
SELECT * FROM employees WHERE hiredate = STR_TO_DATE('4-3 1992', '%c-%d %Y');

#date_format 将日期转换成字符串
SELECT DATE_FORMAT(NOW(),'%y年%月%日') AS out_put;

#查询有奖金的员工名和入职日期（xx月/xx日 xx年）
SELECT last_name, DATE_FORMAT(hiredate, '%m月/%d日 %Y年') AS 入职日期
FROM employees
WHERE commission_pct IS NOT NULL;



# 4.其他函数【补充】
# 查看数据库版本
SELECT VERSION();
# 查看当前数据库
SELECT DATABASE();
# 查看用户
SELECT USER();

# 5.流程控制函数【补充】
# 1. if 函数 ：if else 的效果
SELECT IF(10>5, '大','小');

SELECT last_name,commission_pct, IF(commission_pct IS NULL,'没奖金，呵呵', '有奖金，嘻嘻') AS 备注
FROM employees;

# 2 . case 函数的使用一：类似switch case

/*案例 查询员工的工资要求
部门号=30; 显示的工资为1.1倍
部门号=40; 显示的工资为1.2倍
部门号=50; 显示的工资为1.3倍
其他部门，显示的工资为原来的
*/
SELECT salary AS 原始工资,department_id,
CASE department_id
WHEN 30 THEN salary*1.1
WHEN 40 THEN salary*1.2
WHEN 50 THEN salary*1.3
ELSE salary
END AS 新工资
FROM employees;

# 2 . case 函数的使用二：类似多重if

# 案例：查询员工的工资情况
/*
如果工资>20000,显示A级别
如果工资>15000,显示B级别
如果工资>10000,显示C级别
否者 显示D级别
*/

SELECT salary,
CASE
WHEN salary>20000 THEN 'A'
WHEN salary>15000 THEN 'B'
WHEN salary>10000 THEN 'c'
ELSE 'D'
END AS 工资级别
FROM employees;



 