package entities._06FootballBetting.enums;

public enum CompetitionTypeEnum {

    LOCAL("Local"),
    NATIONAL("National"),
    INTERNATIONAL("International");

    private final String name;

    CompetitionTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
