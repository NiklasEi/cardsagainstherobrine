package me.nikl.cardsagainstherobrine.game;

/**
 * @author Niklas Eicker
 */
public class GameRules {
    private int minPlayers = 2;
    private GameData gameData;
    private int numberOfPlayers = 2;
    private int secondsToPick = 20;
    private int secondsToVote = 20;

    public GameRules(GameData gameData) {
        this.gameData = gameData;
    }

    public GameData getGameData() {
        return gameData;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getSecondsToPick() {
        return secondsToPick;
    }

    public void setSecondsToPick(int secondsToPick) {
        this.secondsToPick = secondsToPick;
    }

    public int getSecondsToVote() {
        return secondsToVote;
    }

    public void setSecondsToVote(int secondsToVote) {
        this.secondsToVote = secondsToVote;
    }
}
