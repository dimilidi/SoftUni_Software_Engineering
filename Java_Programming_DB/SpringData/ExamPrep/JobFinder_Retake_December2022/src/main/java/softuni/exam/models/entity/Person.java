package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;
import softuni.exam.models.entity.enums.StatusTypeEnum;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "people")
public class Person extends BaseEntity {

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;//

    @Column(name = "last_name", nullable = false)
    private String lastName;//

    @Column(nullable = false, unique = true)
    private String email;

    @Column()
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_type", nullable = false)
    private StatusTypeEnum statusType;

    @ManyToOne
    private Country country;
}
