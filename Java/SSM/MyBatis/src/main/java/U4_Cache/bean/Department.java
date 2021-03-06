package U4_Cache.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Alias("DepartmentU4")
public class Department  implements Serializable {

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

    public Department(Integer id) {
        this.id = id;
    }

    public Department() {
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
