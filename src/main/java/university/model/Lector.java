package university.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Lector")
@Table(name = "LECTOR")
public class Lector implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "degree")
    @Enumerated(EnumType.STRING)
    private LectorDegree degree;
    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(mappedBy = "lectors")
    private Set<Department> departments = new HashSet<>();

    public Lector() {
    }

    public Lector(Lector lector) {
    }

    public Lector(String name, LectorDegree degree, Integer salary) {
        this.name = name;
        this.degree = degree;
        this.salary = salary;
    }

    public Lector(String name, LectorDegree degree, Integer salary, Set<Department> departments) {
        this.name = name;
        this.degree = degree;
        this.salary = salary;
        this.departments = departments;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LectorDegree getDegree() {
        return degree;
    }

    public void setDegree(LectorDegree degree) {
        this.degree = degree;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", degree=" + degree +
                ", salary=" + salary +
                '}';
    }
}
