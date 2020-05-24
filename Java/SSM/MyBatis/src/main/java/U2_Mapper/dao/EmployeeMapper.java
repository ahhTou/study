package U2_Mapper.dao;

import U2_Mapper.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EmployeeMapper {

    Employee getEmpByMap(Map<String, Object> map);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpById(Integer id);

    void addEmp(Employee employee);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);


}
