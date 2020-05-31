package MybatisWithSpring.service;

import MybatisWithSpring.bean.Employee;
import MybatisWithSpring.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmpS(){
        return employeeMapper.getEmpS();
    }

}
