package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;
import softuni.exam.models.entity.enums.ApartmentTypeEnum;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ApartmentTypeEnum apartmentType;

    private Double area;

    @ManyToOne
    private Town town;
}
