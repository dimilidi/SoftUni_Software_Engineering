package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "positions")
public class Position  {

    @Id
    @Column(length = 2)
    private String id;

    @Column
    private String description;

    public Position() {}
}