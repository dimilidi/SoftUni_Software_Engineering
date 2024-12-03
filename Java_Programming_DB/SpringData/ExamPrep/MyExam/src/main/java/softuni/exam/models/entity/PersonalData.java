package softuni.exam.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "personal_datas")
public class PersonalData extends BaseEntity {

    @Column
    private Integer age;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(length = 1)
    private Character gender;

}
