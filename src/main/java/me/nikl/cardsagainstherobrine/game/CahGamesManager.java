package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;
import me.nikl.cardsagainstherobrine.inventories.MainGui;
import me.nikl.cardsagainstherobrine.language.CahLanguage;
import me.nikl.inventories.inventory.AbstractInventory;
import me.nikl.inventories.inventory.EasyInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Niklas Eicker
 */
public class CahGamesManager {
    public static final String GROUP_MAIN = "cards-against-herobrine";
    private CardsAgainstHerobrine cardsAgainstHerobrine;
    private GameProvider gameProvider;
    private MainGui mainGui;

    public CahGamesManager(CardsAgainstHerobrine cardsAgainstHerobrine) {
        this.cardsAgainstHerobrine = cardsAgainstHerobrine;
        prepareInventories();
        gameProvider = new GameProvider(cardsAgainstHerobrine);
    }

    private void prepareInventories() {
        AbstractInventory.registerGroup(cardsAgainstHerobrine, GROUP_MAIN);
        mainGui = new MainGui(this);
    }

    public EasyInventory getMainGui() {
        return mainGui;
    }

    public CahGame newGame() {
        return new CahGame(new GameRules(gameProvider.getGameData("Base Set")), this);
    }

    public CardsAgainstHerobrine getPlugin() {
        return cardsAgainstHerobrine;
    }
}
