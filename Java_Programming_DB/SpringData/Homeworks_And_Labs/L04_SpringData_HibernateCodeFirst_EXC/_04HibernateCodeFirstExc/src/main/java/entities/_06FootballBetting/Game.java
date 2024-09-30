package entities._06FootballBetting;

import entities.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @OneToOne
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @Column(name = "home_team_goals")
    private Short homeGoals;

    @Column(name = "away_team_goals")
    private Short awayGoals;

    @Column(name = "date_time")
    private LocalDate dateTime;

    @Column(name = "home_team_win_bet_rate")
    private Double homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private Double awayTeamWinBetRate;

    @Column(name = "draw_game_bet_rate")
    private Double drawGameBetRate;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Game() {}
}