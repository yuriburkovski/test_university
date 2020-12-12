package university.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Department")
@Table(name = "DEPARTMENT")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "head_of_department", unique = true)
    private String headOfDepartment;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
                name = "DEPARTMENT_LECTOR",
                joinColumns = {@JoinColumn(name = "department_id")},
                inverseJoinColumns = {@JoinColumn(name = "lector_id")})
    private Set<Lector> lectors = new HashSet<>();

    public Department() {
    }

    public Department(String departmentName, String headOfDepartment) {
        this.departmentName = departmentName;
        this.headOfDepartment = headOfDepartment;
    }

    public Department(String departmentName, String headOfDepartment, Set<Lector> lectors) {
        this.departmentName = departmentName;
        this.headOfDepartment = headOfDepartment;
        this.lectors = lectors;
    }

    public Integer getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(String headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", headOfDepartment='" + headOfDepartment + '\'' +
                '}';
    }
}
