package entities._06FootballBetting;

import entities.BaseEntity;
import entities._06FootballBetting.enums.ResultPredictionEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private ResultPredictionEnum prediction;

    public ResultPrediction() {}
}