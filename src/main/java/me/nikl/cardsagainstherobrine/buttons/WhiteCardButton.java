package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.cardsagainstherobrine.utility.CardLoreUtility;
import me.nikl.cardsagainstherobrine.utility.ItemStackUtility;
import me.nikl.inventories.button.EasyButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class WhiteCardButton extends EasyButton {
    private WhiteCard whiteCard;
    private CahGame game;
    private boolean selected = false;

    public WhiteCardButton(WhiteCard whiteCard, CahGame game) {
        this.game = game;
        this.whiteCard = whiteCard;
        this.icon = WhiteCard.getWhiteCardIcon();
    }

    @Override
    public void onPreRender() {
        List<String> lore = CardLoreUtility.createCardLore(whiteCard);
        ItemMeta meta = icon.getItemMeta();
        Bukkit.getLogger().info("set name to: " + lore.get(0));
        meta.setDisplayName(lore.get(0));
        lore.remove(0);
        if (!lore.isEmpty())
            meta.setLore(lore);
        icon.setItemMeta(meta);
    }

    @Override
    public void onAfterRender() {
        // nothing to do
    }

    @Override
    public boolean onClick(InventoryClickEvent inventoryClickEvent) {
        return game.selectWhiteCard(this, (Player)inventoryClickEvent.getWhoClicked());
    }

    public void select() {
        if (selected) {
            Bukkit.getLogger().info("should remove one selected...");
            updateIcon(ItemStackUtility.removeGlow(icon));
        } else {
            Bukkit.getLogger().info("should add one selected...");
            updateIcon(ItemStackUtility.addGlow(icon));
        }
        selected = !selected;
    }
}
