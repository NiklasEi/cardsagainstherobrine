package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;
import me.nikl.cardsagainstherobrine.inventories.MainGui;
import me.nikl.inventories.inventory.AbstractInventory;
import me.nikl.inventories.inventory.EasyInventory;

/**
 * @author Niklas Eicker
 */
public class CahGamesManager {
    public static final String GROUP_MAIN = "cards-against-herobrine";
    private CardsAgainstHerobrine cardsAgainstHerobrine;
    private GameProvider gameProvider;
    private MainGui mainGui;
    private GameTimer timer;

    public CahGamesManager(CardsAgainstHerobrine cardsAgainstHerobrine) {
        this.cardsAgainstHerobrine = cardsAgainstHerobrine;
        prepareInventories();
        gameProvider = new GameProvider(cardsAgainstHerobrine);
        this.timer = new GameTimer();
        timer.runTaskTimer(cardsAgainstHerobrine, 2, 2);
    }

    private void prepareInventories() {
        AbstractInventory.registerGroup(cardsAgainstHerobrine, GROUP_MAIN);
        mainGui = new MainGui(this);
    }

    public EasyInventory getMainGui() {
        return mainGui;
    }

    public CahGame newGame() {
        CahGame game = new CahGame(new GameRules(gameProvider.getGameData("Base Set")), this);
        timer.registerGame(game);
        return game;
    }

    public void onGameEnd(CahGame game) {
        timer.removeGame(game);
    }

    public CardsAgainstHerobrine getPlugin() {
        return cardsAgainstHerobrine;
    }
}
