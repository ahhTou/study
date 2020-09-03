package com.ahhtou.pojo;

import com.ahhtou.pojo.dao.DepartmentDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import java.util.Date;

// 员工表
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Date birth;

    private Department department;

    @Resource
    DepartmentDao departmentDao;

    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birth = new Date();
        this.department = department;
    }


}
