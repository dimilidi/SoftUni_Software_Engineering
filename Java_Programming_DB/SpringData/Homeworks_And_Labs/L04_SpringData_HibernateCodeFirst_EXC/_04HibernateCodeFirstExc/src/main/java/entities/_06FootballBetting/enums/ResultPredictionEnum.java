package entities._06FootballBetting.enums;

public enum ResultPredictionEnum {

    HOME_TEAM_WIN("Home Team Win"),
    DRAW_GAME("Draw Game"),
    AWAY_TEAM_WIN("Away Team Win");

    private final String name;

    private ResultPredictionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
