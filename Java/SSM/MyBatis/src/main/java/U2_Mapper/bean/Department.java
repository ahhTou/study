package U2_Mapper.bean;

import java.util.List;

public class Department {

    private Integer id;
    private String departmentName;
    private List<Employee> empS;

    public List<Employee> getEmpS() {
        return empS;
    }

    public void setEmpS(List<Employee> empS) {
        this.empS = empS;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
