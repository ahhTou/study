package U2_Mapper.dao;

import U2_Mapper.bean.Department;
import U2_Mapper.bean.Employee;

import java.util.List;

public interface DepartmentMapper {


    Department getDeptByID(Integer id);

    Department getDeptByIdPlus(Integer id);

    Department getDeptByIdStep(Integer id);

}
