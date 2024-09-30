package services;
/*
_06FootballBetting
•	Teams – Id, Name, Logo, 3 letter Initials (JUV, LIV, ARS…), Primary Kit Color, Secondary Kit Color, Town, Budget
•	Colors – Id, Name
•	Towns – Id, Name, Country
•	Countries – Id (3 letters – for example BUL, USA, GER, FRA, ITA…), Name, Continent
•	Continents – Id, Name
•	Players – Id, Name, Squad Number, Team, Position, Is Currently Injured
•	Position – Id (2 letters – GK, DF, MF, FW…), position description (for example – goal keeper, defender…)
        •	PlayerStatistics – Game, Player, Scored Goals, Player Assists, Played Minutes During Game, (PK = Game + Player)
        •	Games – Id, Home Team, Away Team, Home Goals, Away Goals, Date and Time of Game, Home team Win bet rate, Away Team Win Bet Rate, Draw Game Bet Rate, Round, Competition)
        •	Rounds – Id, Name (for example Groups, League, 1/8 Final, 1/4 Final, Semi-Final, Final…)
•	Competitions – Id, Name, Type (local, national, international)
•	CompetitionTypes – Id, Name
•	BetGame – Game, Bet, Result Prediction (PK = Game + Bet)
•	Bets – Id, Bet Money, Date and Time of Bet, User
•	ResultPrediction – Id, Prediction (possible values - Home Team Win, Draw Game, Away Team Win)
•	Users – Id, Username, Password, Email, Full Name, Balance

The relationships between the tables are as follows:
        •	Team has one primary kit color and one secondary kit color.
•	Team is resident in one town.
        •	Each town can host several teams.
        •	Town can be placed in one country and a country can have many towns.
        •	Country can be placed on several continents and a continent can have many countries.
        •	Player can play for one team and one team can have many players that play for it.
•	Player can play at one position and one position can be played by many players.
•	Player can play in many games and in each game, many players take part.
•	Additionally, for each player for a given game kept statistics such as scored goals, goal assists and minutes played during a given game.
•	A game can be played in one round and in one round many games can be played.
•	A game can be played in one competition and in one competition many games can be played.
•	On a game, many bets can be placed and one bet can be on several games.
•	Each bet for the given game must have a prediction result.
•	A bet can be placed by only one user and one user can place many bets.*/

import jakarta.persistence.EntityManager;
import utils.JpaUtil;

import static enums.PersistenceUnitName.FOOTBALL_BETTING;

public class FootballBettingService {
    private static FootballBettingService instance;

    private FootballBettingService() {}

    public static FootballBettingService getInstance() {
        if (instance == null) {
            instance = new FootballBettingService();
        }
        return instance;
    }

    public void executeTaskSix() {
        EntityManager manager = JpaUtil.getDBConnection(FOOTBALL_BETTING.getPersistenceUnitName());
        manager.getTransaction().begin();

        manager.getTransaction().commit();
    }
}
