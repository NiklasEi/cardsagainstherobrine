package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.inventories.button.EasyButton;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Niklas Eicker
 */
public class WhiteCardButton extends EasyButton {
    private WhiteCard whiteCard;
    private CahGame game;

    public WhiteCardButton(CahGame game, WhiteCard whiteCard) {
        this.game = game;
        this.whiteCard = whiteCard;
        this.icon = WhiteCard.getWhiteCardIcon();

    }

    @Override
    public boolean onClick(InventoryClickEvent inventoryClickEvent) {
        return false;
    }
}
