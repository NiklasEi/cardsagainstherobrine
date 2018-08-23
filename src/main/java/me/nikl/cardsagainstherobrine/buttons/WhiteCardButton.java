package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.cardsagainstherobrine.utility.CardLoreUtility;
import me.nikl.cardsagainstherobrine.utility.ItemStackUtility;
import me.nikl.inventories.button.EasyButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

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
        // nothing to do
    }

    @Override
    public void onAfterRender() {
        updateLore(CardLoreUtility.createCardLore(whiteCard));
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
