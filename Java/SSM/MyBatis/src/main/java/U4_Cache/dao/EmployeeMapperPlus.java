package U4_Cache.dao;

import U4_Cache.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    Employee getEmpAndDept(Integer id);

    Employee getEmpByIdStep(Integer id);

    List<Employee> getEmpSDeptId(Integer deptId);


}
