package U2_Mapper.dao;

import U2_Mapper.bean.Employee;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    Employee getEmpAndDept(Integer id);
}
