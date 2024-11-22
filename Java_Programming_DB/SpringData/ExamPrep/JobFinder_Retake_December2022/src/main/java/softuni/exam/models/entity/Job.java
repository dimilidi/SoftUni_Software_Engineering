package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Double salary;

    @Column(name = "hoursaweek", nullable = false)
    private Double hoursAWeek;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Company company;

    @Override
    public String toString() {
        return "Job title " + title + "\n" +
                "    -Salary: " + String.format("%.2f", salary) + "$\n" +
                "    --Hours a week: " + String.format("%.2f", hoursAWeek) + "h.\n" ;
    }
}
