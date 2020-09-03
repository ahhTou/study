package com.ahhtou.pojo.dao;

import com.ahhtou.pojo.Department;
import com.ahhtou.pojo.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 部门dao
@Repository
public class DepartmentDao {

    // 模拟数据库中的数据
    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<>(); // 创建一个部门

        departments.put(101, new Department(101, "A部))"));
        departments.put(102, new Department(101, "B部))"));
        departments.put(103, new Department(101, "C部))"));
        departments.put(104, new Department(101, "D部))"));
        departments.put(105, new Department(101, "E部))"));
    }

    // 获取所有部门信息
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    // 通过id得到部门
    public Department getDepartmentById(Integer id) {
        return departments.get(id);
    }


}
