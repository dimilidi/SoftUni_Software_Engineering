package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column
    private String name;

    @Column(name = "squad_number")
    private Integer squadNumber;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Position position;

    @Column(name = "is_injured", columnDefinition = "BOOLEAN")
    private Boolean isInjured;

    public Player() {}
}