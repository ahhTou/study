# 排序查询

/*

引入：	
	select * from employees;
语法：
	select 查询列表
	from 表
	【where 筛选条件】
	order by 排序列表

*/

# 案:1： 查询员工信息，要求工资从高到低
USE sakila;
SELECT * FROM `employees` ORDER BY salary DESC; #DESC 降序
SELECT * FROM `employees` ORDER BY salary ASC; #默认ASC 升序

# 案例2：查询部门编号>=90 的员工信息，俺入职时间的先后时间进行排序【添加筛选条件】
SELECT * 
FROM employees 
WHERE department_id >=90
ORDER BY hiredate ASC;

# 案例3 ： 按年薪的高低显示员工的信息和年薪 【按表达式排序或者表达式】
SELECT *, salary*12*(1+IFNULL(commission_pct, 0)) AS 年薪
FROM employees
# order by salary*12*(1+IFNULL(commission_pct, 0)) desc;
ORDER BY 年薪 DESC;

# 案例4： 按姓名长度来显示姓名和工资【按函数排序】
SELECT LENGTH(last_name) 字节长度,last_name,salary
FROM employees
ORDER BY LENGTH(last_name) DESC;

# 案例5：查询员工信息，要求按工资升序，再按员工编号降序 【按多个字段排序】
SELECT * 
FROM employees
ORDER BY salary ASC,employee_id DESC; # 类似主要关键字和次要关键字
