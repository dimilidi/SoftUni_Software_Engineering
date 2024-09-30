package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type")
    private CompetitionType competitionType;

    public Competition() {}
}
