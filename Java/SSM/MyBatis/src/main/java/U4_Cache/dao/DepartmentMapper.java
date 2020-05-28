package U4_Cache.dao;

import U4_Cache.bean.Department;

public interface DepartmentMapper {


    Department getDeptByID(Integer id);

    Department getDeptByIdPlus(Integer id);

    Department getDeptByIdStep(Integer id);

}
