package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.inventories.button.EasyButton;
import me.nikl.inventories.hooks.ChangeableLore;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class CahGameButton extends EasyButton implements ChangeableLore {
    private CahGame game;

    public CahGameButton(CahGame game) {
        super(game.getIcon());
        this.game = game;
    }

    @Override
    public boolean onClick(InventoryClickEvent inventoryClickEvent) {
        game.join((Player) inventoryClickEvent.getWhoClicked());
        return true;
    }

    @Override
    public void updateLore(List<String> list) {
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(list);
        icon.setItemMeta(meta);
        getParent().addButton(getSlot(), this);
    }
}
