package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.FootballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;


    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        setName(name);
        setEndurance(endurance);
        setSprint(sprint);
        setDribble(dribble);
        setPassing(passing);
        setShooting(shooting);
    }

    private void setName(String name) {
       Validators.validateName(name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private void setEndurance(int endurance) {
        validateStat("Endurance", endurance);
        this.endurance = endurance;
    }


    private void setSprint(int sprint) {
        validateStat("Sprint", sprint);
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        validateStat("Dribble", dribble);
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        validateStat("Passing", passing);
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        validateStat("Shooting", shooting);
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        int totalSKills = this.endurance + this.sprint + this.dribble + this.passing + this.shooting;
        return totalSKills / 5.0;
    }

    private static void validateStat( String statName, int statValue) {
        if(statValue < 0 || statValue > 100) {
            throw new IllegalArgumentException(statName + " should be between 0 and 100.");
        }
    }
}
