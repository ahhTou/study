package U2_Mapper.dao;

import U2_Mapper.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    Employee getEmpAndDept(Integer id);

    Employee getEmpByIdStep(Integer id);

    List<Employee> getEmpSDeptId(Integer deptId);


}
