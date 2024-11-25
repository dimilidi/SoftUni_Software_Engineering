package com.example.football.models.entity;

import com.example.football.models.entity.base.BaseEntity;
import com.example.football.models.entity.enums.PositionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.ORDINAL)
    private PositionTypeEnum position;

    @ManyToOne
    private Town town;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Stat stat;

    @Override
    public String toString() {
        return "Player - " + firstName + " " + lastName + "\n" +
                "\tPosition - " + position + "\n" +
                "\tTeam - " + team.getName() + "\n" +
                "\tStadium - " + team.getStadiumName() + "\n";
    }
}
