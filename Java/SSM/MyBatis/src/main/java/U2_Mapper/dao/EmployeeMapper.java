package U2_Mapper.dao;

import U2_Mapper.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    // 多条记录封装一个map: Map< Integer , Employee> :键匙这条记录的主键，值是记录封装后的JavaBean
    @MapKey("id") //告诉mybatis封装这个map的时候使用哪个属性作为map的key
    Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    // 返回一条记录的map，key是列名，值就是对应的值
    Map<String, Object> getEmpByIdReturnMap(Integer id);



    List<Employee> getEmpByLastNameLike(String lastName);

    Employee getEmpByMap(Map<String, Object> map);

    Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

    Employee getEmpById(Integer id);

    void addEmp(Employee employee);

    boolean updateEmp(Employee employee);

    void deleteEmpById(Integer id);


}
