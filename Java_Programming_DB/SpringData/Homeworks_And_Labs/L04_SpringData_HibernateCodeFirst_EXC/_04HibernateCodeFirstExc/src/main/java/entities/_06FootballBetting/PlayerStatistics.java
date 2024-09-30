package entities._06FootballBetting;

import jakarta.persistence.*;

@Entity(name = "PlayerStatistics")
@Table(name = "player_statistics")
public class PlayerStatistics {

    @EmbeddedId
    private GamePlayerId id;

    @ManyToOne
    @MapsId("gameId")
    private Game game;

    @ManyToOne
    @MapsId("playerId")
    private Player player;

    @Column(name = "scored_goals")
    private Short scoredGoals;

    @Column(name = "player_assists")
    private Short playerAssists;

    @Column(name = "played_minutes")
    private Short playedMinutes;

    public PlayerStatistics() {}
}