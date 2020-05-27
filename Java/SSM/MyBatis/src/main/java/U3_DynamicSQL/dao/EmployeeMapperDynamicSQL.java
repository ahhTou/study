package U3_DynamicSQL.dao;

import U3_DynamicSQL.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface EmployeeMapperDynamicSQL {

    // 要求携带了哪个字段查询条件就带上这个字段的值
    List<Employee> getEmpSByConditionIf(Employee employee);

    List<Employee> getEmpSByConditionTrim(Employee employee);

    List<Employee> getEmpSByConditionChoose(Employee employee);

    void updateEmpSet(Employee employee);

    void updateEmpTrimSet(Employee employee);


    List<Employee> getEmpSByConditionForeach(@Param("listPro") ArrayList<Integer> ids);

    void addEmpS(@Param("empS") List<Employee> employees);

    List<Employee> getEmpSTestInnerParam(Employee employee);

}
