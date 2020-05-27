package U3_DynamicSQL.dao;

import U3_DynamicSQL.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    Employee getEmpAndDept(Integer id);

    Employee getEmpByIdStep(Integer id);

    List<Employee> getEmpSDeptId(Integer deptId);


}
