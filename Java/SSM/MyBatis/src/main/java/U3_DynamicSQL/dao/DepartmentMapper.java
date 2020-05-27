package U3_DynamicSQL.dao;

import U3_DynamicSQL.bean.Department;

public interface DepartmentMapper {


    Department getDeptByID(Integer id);

    Department getDeptByIdPlus(Integer id);

    Department getDeptByIdStep(Integer id);

}
