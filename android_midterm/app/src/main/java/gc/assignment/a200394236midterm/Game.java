package gc.assignment.a200394236midterm;

public class Game {
    String GameId;
    String FirstName;
    String LastName;
    String Choice;
    String Winner;

    public Game() {
    }

    public Game(String gameId, String firstName, String lastName, String choice, String winner) {
        GameId = gameId;
        FirstName = firstName;
        LastName = lastName;
        Choice = choice;
        Winner = winner;
    }

    public String getGameId() {
        return GameId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getChoice() {
        return Choice;
    }

    public String getWinner() {
        return Winner;
    }
}
