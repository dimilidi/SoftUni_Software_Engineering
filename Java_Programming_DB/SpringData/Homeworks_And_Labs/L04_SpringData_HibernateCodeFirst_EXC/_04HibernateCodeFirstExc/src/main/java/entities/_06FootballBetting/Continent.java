package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {

    @Column
    private String name;

    @ManyToMany(targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinTable(name = "continents_countries",
            joinColumns = @JoinColumn(name = "continent_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> countries;

    public Continent() {
        this.countries = new HashSet<>();
    }

}