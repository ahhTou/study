package com.ahhtou.pojo.dao;

import com.ahhtou.pojo.Department;
import com.ahhtou.pojo.Employee;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 员工dao
public class EmployeeDao {

    // 模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    @Resource
    private Department department;

    static {
        employees = new HashMap<>(); // 创建一个部门

        employees.put(1001, new Employee(101, "AA", "123@qq.com", 1, new Department(101, "E部")));
        employees.put(1002, new Employee(102, "AA", "123@qq.com", 1, new Department(101, "E部")));
        employees.put(1003, new Employee(103, "AA", "123@qq.com", 1, new Department(101, "E部")));
        employees.put(1004, new Employee(104, "AA", "123@qq.com", 1, new Department(101, "E部")));
        employees.put(1005, new Employee(105, "AA", "123@qq.com", 1, new Department(101, "E部")));
    }

    // 主键自增
    private static Integer initID = 1006;

    // 增加一个员工
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initID++);
        }
        employees.put(employee.getId(), employee);
    }

    // 查询全部员工信息
    public Collection<Employee> getAll() {
        return employees.values();
    }

    // 通过id查询员工
    public Employee getEmployeeById(Integer id) {
        return employees.get(id);
    }

    // 删除员工通过id
    public void deleteEmployeeById(Integer id) {
        employees.remove(id);
    }
}
