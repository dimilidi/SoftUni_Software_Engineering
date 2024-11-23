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
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "published_on", nullable = false)
    private LocalDate publishedOn;

    @ManyToOne
    private Apartment apartment;

    @ManyToOne
    private Agent agent;

    @Override
    public String toString() {
        return "Agent " + agent.getFirstName() + " " + agent.getLastName() + " with offer №" + this.getId() + ":\n" +
                " -Apartment area: " + apartment.getArea() + "\n" +
                " --Town: " + apartment.getTown().getTownName()+ "\n" +
                " ---Price: " + this.getPrice() + "$\n";
    }
}
