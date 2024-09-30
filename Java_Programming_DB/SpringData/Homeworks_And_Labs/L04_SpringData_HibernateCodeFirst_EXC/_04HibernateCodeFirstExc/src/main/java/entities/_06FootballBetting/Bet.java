package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity {

    @Column(name = "bet_money", columnDefinition = "DECIMAL(19, 2)")
    private BigDecimal betMoney;

    @Column(name = "date_time")
    private LocalDate dateTime;

    @ManyToOne
    private User user;

    public Bet() {}

}