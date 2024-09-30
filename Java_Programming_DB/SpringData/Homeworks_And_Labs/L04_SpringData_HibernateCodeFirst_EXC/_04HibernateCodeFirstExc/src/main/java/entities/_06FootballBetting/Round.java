package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "rounds")
public class Round extends BaseEntity {

    @Column
    private String name;

    public Round() {}
}