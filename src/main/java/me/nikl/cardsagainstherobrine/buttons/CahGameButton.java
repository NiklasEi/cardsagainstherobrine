package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.inventories.button.EasyButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Niklas Eicker
 */
public class CahGameButton extends EasyButton {
    private CahGame game;

    public CahGameButton(CahGame game, ItemStack icon) {
        super(icon);
        this.game = game;
    }

    @Override
    public boolean onClick(InventoryClickEvent inventoryClickEvent) {
        game.join((Player) inventoryClickEvent.getWhoClicked());
        return true;
    }

    @Override
    public void onPreRender() {
        // nothing to do
    }

    @Override
    public void onAfterRender() {
        // nothing to do
    }
}
