package softuni.exam.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;
import softuni.exam.models.entity.enums.VolcanoTypeEnum;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "volcanoes")
public class Volcano extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer elevation;

    @Enumerated(EnumType.STRING)
    @Column(name = "volcano_type")
    private VolcanoTypeEnum volcanoType;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "last_eruption", columnDefinition = "DATE")
    private Date lastEruption;

    @ManyToOne
    private Country country;
}
