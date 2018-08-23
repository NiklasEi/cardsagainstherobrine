package me.nikl.cardsagainstherobrine.inventories;

import me.nikl.cardsagainstherobrine.game.CahGamesManager;
import me.nikl.inventories.button.EasyButton;
import me.nikl.inventories.inventory.EasyInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Niklas Eicker
 */
public class MainGui extends EasyInventory {
    private CahGamesManager manager;

    public MainGui(CahGamesManager manager) {
        super(CahGamesManager.GROUP_MAIN, 54, "CAH");
        this.manager = manager;
        addButtons();
    }

    private void addButtons() {
        addButton(49, new StartGameButton(manager));
    }

    private class StartGameButton extends EasyButton {
        private CahGamesManager manager;

        public StartGameButton(CahGamesManager manager) {
            this.manager = manager;
        }

        @Override
        public boolean onClick(InventoryClickEvent inventoryClickEvent) {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            manager.newGame().join(player);
            return true;
        }

        @Override
        public void onPreRender() {
            this.icon = new ItemStack(Material.COOKIE);
        }

        @Override
        public void onAfterRender() {
            // nothing to do
        }
    }
}
