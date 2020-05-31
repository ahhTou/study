package MybatisWithSpring.controller;

import MybatisWithSpring.bean.Employee;
import MybatisWithSpring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/getEmpS")
    public void empS() {
        System.out.println("进来了");
        try {
            List<Employee> empS = employeeService.getEmpS();
            System.out.println("===");
            System.out.println(empS);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("出错了");
        }
    }
}
