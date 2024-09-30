package entities._06FootballBetting;


import entities.BaseEntity;
import entities._06FootballBetting.enums.CompetitionTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "competition_type")
public class CompetitionType extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private CompetitionTypeEnum name;

    public CompetitionType() {
    }
}