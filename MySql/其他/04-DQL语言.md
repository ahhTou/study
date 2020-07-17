

# DQL语言的学习

> 准备文件：[点击前往](https://blog.csdn.net/GongmissYan/article/details/102937816)

### 基础查询

##### 查询 单个、多个、全部 字段

 ```mysql
 USE 数据库名;
 
 #查询单个 字段
 SELECT 字段名 FROM 表名;
 
 #查询多个 字段
 SELECT 字段1, 字段2 FROM 表名;
 
 #查询表中的 所有字段
 SELECT * FROM 表名;
 ```

##### 查询 常量、表达式、函数

```mysql
# 查询常量值
SELECT 'john';

# 查询表达式
SELECT 100%98;

# 查询函数
SELECT VERSION();

#字段 着重号 防止关键字冲突
SELECT `name` FROM 表名;
```

##### 起别名案例

```mysql
SELECT	salary AS "out put" FROM employees;
```

##### 去重

```mysql
SELECT DISTINCT department_id FROM employees;
```

##### 拼接字符串

```mysql
SELECT 
	CONCAT (last_name , first_name) AS 姓名 
FROM
	employees;
```

##### 查看表结构

```mysql
DESC departments;
```

##### 案例拼接字符串函数和ifnull函数

```mysql
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
```



------



### 条件查询

#### 语法

```mysql
select
	查询列表
from	
	表名
where
	筛选条件
```

#### 分类

	1.按条件表达式筛选
		条件运算符：> < = != <> >= <=
	2.按逻辑表达式查询
		逻辑运算符
		&& || ！
		and or not 
	3.模糊查询
		like
		between and 
		in
		is umll



#### 按照条件表达式查询

##### 几个案例

```mysql
#  案例1:查询工资>12000的
SELECT
	*
FROM
	employees
WHERE
	salary>12000;
	
# 案例2:部门编号不等于90号的员工名和部门编号
SELECT 
	last_name,
	department_id
FROM 
	employees
WHERE
	department_id<>90;
```



#### 按逻辑表达式查询

##### 注意

NOT 仅支持 IN, BETWEEN, EXISTS 句子前取反

##### 几个案例

```mysql
# 案例1：查询工资在1w到2w之间的员工名，工资，以及奖金
SELECT 
	last_name,
	salary,
	commission_pct
FROM
	employees
WHERE	
	salary>=10000 AND salary<=20000;

#案例2： 查询部门编号不是在90到110之间，或者工资高于15000的员工名单
SELECT
	*
FROM
	employees
WHERE
	NOT(department_id<90 OR department_id >110) OR salary >15000;

```





#### 模糊查询

##### LIKE 和 通配符

```mysql
#案例1 ： 查询员工名中包含字符a的员工信息
SELECT
	*
FROM
	employees
WHERE
	last_name LIKE '%a%';
	
#

#案例2 ： 查询庺中第三个字符为e, 第五个字符为a的员工的工资
SELECT 
	last_name,
	salary
FROM
	employees
WHERE
	last_name LIKE '__n_a%';
	
#案例3： 查询员工名中第三个字符为_的员工名
SELECT 
	last_name,
	salary
FROM
	employees
WHERE
	last_name LIKE '_\_%';
#指定转义符 last_name LIKE '_\_%' ESCAPE '$';
```

#####  BETWEEN AND

包含临界值，不可以调换

```mysql
SELECT 
	*
FROM
	employees
WHERE
	employee_id BETWEEN 100 AND 120;
```

##### in

判断某字段的值是否属于in列表的某一项。而且in列表类型必须是同类型或者兼容

```mysql
#案例：查询员工的工种名 编号是 IT_PROG、AD_VP、AD_PRES 中的一个员工名 和 工种编号
SELECT 
	last_name,
	job_id
FROM
	employees
WHERE
	job_id IN('IT_PROT','AD_VP','AD_PRES');
```



##### IS NULL

=不能判断null <=> 可以判断null值也可以判断其他，但是可读性较低

```mysql
#案例1 ： 查询没有奖金的员工名和奖金率
SELECT 
	last_name,
	commission_pct
FROM
	employees
WHERE
	commission_pct IS NOT NULL
	commission_pct IS NULL
```



------





### 排序查询

#### 1. 单字段排序

```mysql
# 案:1： 查询员工信息，要求工资从高到低
USE sakila;
SELECT * FROM `employees` ORDER BY salary DESC; #DESC 降序
SELECT * FROM `employees` ORDER BY salary ASC; #默认ASC 升序
```

####  2. 带筛选条件的排序

```mysql
# 案例2：查询部门编号>=90 的员工信息，俺入职时间的先后时间进行排序
SELECT * 
FROM employees 
WHERE department_id >=90
ORDER BY hiredate ASC;
```

#### 3. 按表达式排序

```mysql
# 案例3 ： 按年薪的高低显示员工的信息和年薪
SELECT *, salary*12*(1+IFNULL(commission_pct, 0)) AS 年薪
FROM employees
# order by salary*12*(1+IFNULL(commission_pct, 0)) desc;
ORDER BY 年薪 DESC;
```

#### 4. 按函数排序
```mysql
# 案例4： 按姓名长度来显示姓名和工资
SELECT LENGTH(last_name) 字节长度,last_name,salary
FROM employees
ORDER BY LENGTH(last_name) DESC
```

#### 5. 多个字段排序
```mysql
# 案例5：查询员工信息，要求按工资升序，再按员工编号降序
SELECT * 
FROM employees
ORDER BY salary ASC,employee_id DESC; # 类似主要关键字和次要关键字

```



------

### 常见函数

#### 介绍

> 概念:类似于java的方法，将一组逻辑语句封装在方法体中，对外暴露方法名
> 好处: 1、隐藏了实现细节2、 提高代码的重用性
> 调用: select 函数名(实参列表)[ from 表] ;
> 特点:
> 	①叫什么(函数名)
> 	②千什么( 函数功能)
> 分类:
> 	1、单行函数
> 	如concat、 length、 ifnull等
> 	2、分组函数
> 	功能:做统计使用，又称为统计函数、聚合函数、组函数
>
>  1.单行函数，字符函数
>  2.数学函数
>  3.日期函数
>  4.其他函数【补充】
>  5.流程控制函数【补充】

#### 单行函数

##### 1. length
> 返回长度

```mysql
SELECT LENGTH('john');
SELECT LENGTH('张三丰hahaha');
```

##### 2. concat
> 连接
```mysql
SELECT CONCAT(last_name, '_', first_name) AS 姓名 FROM employees;
```

##### 3. upper lower
> 转换大小写
```mysql
SELECT UPPER('john');
SELECT LOWER('joHn');
SELECT CONCAT(UPPER(last_name),'_',LOWER(first_name)) AS 姓名 FROM employees;
```

##### 4. substr substring
> 字符串裁切
```mysql
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
```

##### 5. instr
> 返回子串第一次出现的索引
```mysql
SELECT INSTR('ahhTou', 'Tou') AS out_put;
```

##### 6. trim
> 去除首位空格
```mysql
SELECT TRIM('     ahhTou      ') AS out_put;

SELECT TRIM('x' FROM 'xxxxxxxxxxahhTouxxxxxxxxx') AS out_put;
```

##### 7. lpad rpad
> 左填充，超出截断
```mysql
SELECT LPAD('ahhTou',10,'*') AS output;
SELECT RPAD('ahhTou',10,'*') AS output;
```
##### 8. replace
> 替换
```mysql
SELECT REPLACE('ProahhTouPro','Pro','Pro你妈呢，傻狗') AS out_put;

SHOW VARIABLES LIKE '%char%' ;
```

#### 数学函数

##### 1.round

>四舍五入
```mysql
SELECT ROUND(1.45);
SELECT ROUND(1.453,1);
```

##### 2.ceil
>向上取整
```mysql
SELECT CEIL(1.02);
```

##### 3.floor
>向下取整
```mysql
SELECT CEIL(1.02);
```

##### 4.truncate
>截断
```mysql
SELECT TRUNCATE(1.59999,1);
```

##### 5.mod
>取余 mod(a,b) a-a/b*b
```mysql
SELECT MOD(10,-3);
```
#### 日期函数

##### 1.now
> 返回当前系统时间 + 时间
```mysql
SELECT NOW();
```

##### 2.curdata
> 返回当前系统日期，不包含时间
```mysql
SELECT CURDATE();
```

##### 3.curtime 
> 返回当前时间 ，不包含日期
```mysql
SELECT CURTIME();
```

##### 4.获取指定的部分
> 可以获取指定的部分，年、月、日、小时、分钟、秒
```mysql
SELECT YEAR(NOW());
SELECT YEAR('1999-1-1');

SELECT YEAR(hiredate) AS 年 FROM employees;

SELECT MONTH(NOW()) 月;
SELECT MONTHNAME(NOW()) 月;
```

##### 5.str_to_data
> 指定的格式转化成日期
```mysql
SELECT STR_TO_DATE('1998-3-2','%Y-%c-%d') AS out_put;

# 查询入职日期为1992-4-3员工日期
SELECT * FROM employees WHERE hiredate = '1992-4-3';
SELECT * FROM employees WHERE hiredate = STR_TO_DATE('4-3 1992', '%c-%d %Y');
```

##### 6.date_format
> 将日期转换成字符串
```mysql
SELECT DATE_FORMAT(NOW(),'%y年%月%日') AS out_put;

#查询有奖金的员工名和入职日期（xx月/xx日 xx年）
SELECT last_name, DATE_FORMAT(hiredate, '%m月/%d日 %Y年') AS 入职日期
FROM employees
WHERE commission_pct IS NOT NULL;
```

#### 流程控制函数

##### 1.if
> if else 的效果
```mysql
SELECT IF(10>5, '大','小');

SELECT last_name,commission_pct, IF(commission_pct IS NULL,'没奖金，呵呵', '有奖金，嘻嘻') AS 备注
FROM employees;
```
##### 2.case
> 可以实现类似switch case 和 多重if

```mysql
函数的使用一：类似switch case

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

/*case 函数的使用二：类似多重if
# 案例：查询员工的工资情况
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
```

#### 其他函数

##### 查看数据库版本
```mysql
SELECT VERSION();
```

##### 查看当前数据库
```mysql
SELECT DATABASE();
```

##### 查看用户
```mysql
SELECT USER();
```
------

### 分组函数

#### 功能

> 用作统计使用，又称为聚合函数或者统计函数或组函数

#### 简单使用

```mysql
SELECT SUM(salary) FROM employees; # 求和
SELECT AVG(salary) FROM employees; # 平均值
SELECT MIN(salary) FROM employees; # 最小值
SELECT MAX(salary) FROM employees; # 最大值
SELECT COUNT(salary) FROM employees; # 计算个数
```

#### 兼容性

> sum、AVG 一般用于处理数值型
> max、min. count可 以处理任何类型

#### DISTINCT 搭配实现去重

```mysql
SELECT COUNT(DISTINCT salary), COUNT(salary)
FROM employees;
```

#### COUNT 补充

##### 统计行数

```mysql
SELECT COUNT(salary) FROM employees;

SELECT COUNT(*) FROM employees;

#每一列添加一个1 来统计多少个1（统计多少列）
SELECT COUNT(1) FROM employees; 
```

##### 效率

> MYISAM 的存储引擎下，count() 的效率高
> INNOB 的存储引擎下 ，count()和count(字段)效率差不多，前者更高一点

#### 问题

```mysql
# 6.和分组函数一同查询的字段有限制
SELECT AVG(salary),employees_id FROM employees;
```

------

### 分组查询

#### 语法

```mysql
select分组函数,列(要求出现在group by的后面)
from表
[where筛选条件]
group by分组的列表
[order by子句]
```

#### 注意

> 查询列表必须特殊，要求是分组函数和group by后出现的字段

#### 特点

> 1、分组查询中的筛选条件分为两类
>
> |            | 数据源         | 位置               | 关键字 |
> | ---------- | -------------- | ------------------ | ------ |
> | 分组前筛选 | 原始表         | group by子句的前面 | where  |
> | 分组后筛选 | 分组后的结果集 | group by子句的后面 | having |
>
> 
>
> ①分组函数做条件肯定是放在 **HAVING** 子句中
> ②能用分组前筛选的， 就优先考虑使用分组前筛选
> 2、group by子句支持单个字段分组，多个字段分组(多个字段之间用逗号隔开没有顺序要求)，表达式，或函数
> 3、也可以添加排序(排序放在整个分组查询的最后)

#### 案例

##### 基础案例

```mysql
# 案例1： 查询每个工种的最高工资
SELECT MAX(salary), job_id
FROM employees
GROUP BY job_id;

# 案例2： 查询每个位置上的部门个数
SELECT COUNT(*) ,location_id
FROM departments
GROUP BY location_id;
```

##### 添加筛选条件

```mysql
# 案例1 ： 查询邮箱中包含a字符，每个部门的平均工资
SELECT AVG(salary), department_id
FROM employees
WHERE email LIKE '%a%'
GROUP BY department_id;

#案例2:查询有奖金的每个领导手下员工的最高工资
SELECT MAX(salary),manager_id
FROM employees
WHERE commission_pct IS NOT NULL
GROUP BY manager_id;
```

##### 添加排序后的筛选条件

```mysql
#案例2:查询每个工种有奖金的员工的最高工资>12000的工种编号和最高工资
SELECT 
  MAX(salary),
  job_id 
FROM
  employees 
WHERE commission_pct IS NOT NULL 
GROUP BY job_id
HAVING MAX(salary) > 12000 ;
```

##### 结合表达式别名

```mysql
SELECT COUNT(*),LENGTH(last_name) AS len_name
FROM employees
GROUP BY len_name;
HAVING COUNT(*) > 5 ;
```

##### 多字段分组查询

```mysql
#案例:查询每个部门每个工种且不为空的员工的平均工资，并且按照平均工资的高低排序并排除低于10000的
SELECT AVG(salary), department_id,job_id # 两个字段一样 则 分为一组
FROM employees
WHERE department_id IS NOT NULL
GROUP BY department_id ,job_id
HAVING AVG(salary) > 10000;
ORDER BY AVG(salary) DESC;
```



------

### 连接查询

------

### 子查询

------

### 分页查询

------

### union联合查询

