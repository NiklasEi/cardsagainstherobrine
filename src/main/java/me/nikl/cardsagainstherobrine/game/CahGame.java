package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.buttons.CahGameButton;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.inventories.inventory.EasyInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import me.nikl.inventories.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Niklas Eicker
 */
public class CahGame {
    private ItemStack icon;
    private Inventory gameInventory;
    private CahGamesManager manager;
    private List<Player> players = new ArrayList<>();
    private Map<UUID, List<WhiteCard>> playerCards = new HashMap<>();

    public CahGame(Player owner, CahGamesManager manager, GameData gameData) {
        gameInventory = new EasyInventory(CahGamesManager.GROUP_MAIN, InventoryType.CHEST, "Game");
        this. manager = manager;
        icon = new ItemStack(Material.PAPER);
        manager.getMainGui().addButtons(new CahGameButton(this));
        join(owner);
    }

    public Inventory getGameInventory() {
        return gameInventory;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void join(Player whoClicked) {
        gameInventory.open(whoClicked);
        players.add(whoClicked);
    }
}
