package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "volcanologists")
public class Volcanologist extends BaseEntity {

    //@Size(min = 2, max = 30)
    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    //@Size(min = 2, max = 30)
    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    //@Positive
    @Column( nullable = false)
    private Double salary;

    //@Size(min = 18, max = 80)
    @Column( nullable = false)
    private Integer age;

    @Column(name = "exploring_from", columnDefinition = "DATE")
    private Date exploringFrom;

    @ManyToOne
    private Volcano exploringVolcano;
}
