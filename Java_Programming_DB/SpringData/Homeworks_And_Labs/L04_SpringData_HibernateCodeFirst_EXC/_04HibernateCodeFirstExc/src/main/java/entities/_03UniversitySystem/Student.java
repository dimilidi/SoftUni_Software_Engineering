package entities._03UniversitySystem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name = "students")
public class Student extends Person {

    @Column(name = "average_grade", columnDefinition = "DECIMAL(10, 2)")
    private Double averageGrade;

    @Column
    private Integer attendance;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<>();

    public Student() {
        super();
    }

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
