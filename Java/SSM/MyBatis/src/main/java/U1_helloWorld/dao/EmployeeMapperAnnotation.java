package U1_helloWorld.dao;

import U1_helloWorld.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("select * from tab1_employee where id = #{id}")
    Employee getEmpById(Integer id);
}
