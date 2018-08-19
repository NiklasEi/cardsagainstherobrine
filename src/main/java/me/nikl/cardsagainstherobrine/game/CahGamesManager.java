package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;
import me.nikl.inventories.inventory.AbstractInventory;
import me.nikl.inventories.inventory.EasyInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Niklas Eicker
 */
public class CahGamesManager implements Listener {
    public static final String GROUP_MAIN = "cards-against-herobrine";
    private CardsAgainstHerobrine cardsAgainstHerobrine;
    private GameProvider gameProvider;
    private EasyInventory mainGui;

    public CahGamesManager(CardsAgainstHerobrine cardsAgainstHerobrine) {
        this.cardsAgainstHerobrine = cardsAgainstHerobrine;
        prepareInventories();
        gameProvider = new GameProvider(cardsAgainstHerobrine);

        Bukkit.getPluginManager().registerEvents(this, cardsAgainstHerobrine);
    }

    private void prepareInventories() {
        AbstractInventory.registerGroup(cardsAgainstHerobrine, GROUP_MAIN);
        mainGui = new EasyInventory(GROUP_MAIN, InventoryType.CHEST, "CAH");
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // start test game with joined player
        Player gameOwner = event.getPlayer();
        new CahGame(gameOwner, this, gameProvider.getGameData("Base Set"));
    }

    public EasyInventory getMainGui() {
        return mainGui;
    }
}
