package softuni.exam.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.base.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attractions")
public class Attraction extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer elevation;

    @Column(nullable = false)
    private String type;

    @ManyToOne(optional = false)
    private Country country;

    @Override
    public String toString() {
        return "Attraction with ID"+ this.getId() + ":\n" +
                "***" + name + " - "+ description + " at an altitude of " + elevation + "m. somewhere in " + country.getName() + ".\n";
    }
}
