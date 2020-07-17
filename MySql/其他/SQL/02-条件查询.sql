/*

select
	查询列表
from	
	表名
where
	筛选条件

分类：
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

*/

# 安条件表达式筛选
# 案例1:查询工资>12000的
SELECT
	*
FROM
	employees
WHERE
	salary>12000;
	
# 案例2：部门编号不等于90号的员工名和部门编号
SELECT 
	last_name,
	department_id
FROM 
	employees
WHERE
	department_id<>90;

# 按 逻辑表达式筛选
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
	
# 3.模糊查询
/*
	通配符
	% 任意多个字符 包含0个
	_ 任意单个字符
	
	
*/
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

# 2.between and 包含临界值，不可以调换
#案例1 ： 查询员工编号在100 到 120 之间 的员工信息
SELECT 
	*
FROM
	employees
WHERE
	employee_id BETWEEN 100 AND 120;
	

# 3.in 判断某字段的值是否属于in列表的某一项。而且in列表类型必须是同类型或者兼容
#案例：查询员工的工种名 编号是 IT_PROG、AD_VP、AD_PRES 中的一个员工名 和 工种编号
SELECT 
	last_name,
	job_id
FROM
	employees
WHERE
	job_id IN('IT_PROT','AD_VP','AD_PRES');
	
	
# 4.is null =不能判断null <=> 可以判断null值也可以判断其他，但是可读性较低
#案例1 ： 查询没有奖金的员工名和奖金率
SELECT 
	last_name,
	commission_pct
FROM
	employees
WHERE
	commission_pct IS NOT NULL
	commission_pct IS NULL