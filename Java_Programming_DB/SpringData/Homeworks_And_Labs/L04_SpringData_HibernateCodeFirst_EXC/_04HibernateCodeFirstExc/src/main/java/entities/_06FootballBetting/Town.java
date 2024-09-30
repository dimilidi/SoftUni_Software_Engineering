package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    public Town() {}
}