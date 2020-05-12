

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



------

### 分组函数

------

### 分组查询

------

### 连接查询

------

### 子查询

------

### 分页查询

------

### union联合查询

