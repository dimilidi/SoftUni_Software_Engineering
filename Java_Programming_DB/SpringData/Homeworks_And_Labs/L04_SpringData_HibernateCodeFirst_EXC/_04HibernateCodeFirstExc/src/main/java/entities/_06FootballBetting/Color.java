package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "colors")
public class Color extends BaseEntity {

    @Column
    private String name;

    public Color() {}
}