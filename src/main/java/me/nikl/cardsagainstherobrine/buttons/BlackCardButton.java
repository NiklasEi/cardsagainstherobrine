package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.cardsagainstherobrine.utility.CardLoreUtility;
import me.nikl.inventories.button.EmptyIconButton;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class BlackCardButton extends EmptyIconButton {
    private CahGame game;
    private BlackCard blackCard;

    public BlackCardButton(BlackCard blackCard, CahGame game) {
        this.game = game;
        this.blackCard = blackCard;
        this.icon = BlackCard.getBlackCardIcon();
        List<String> lore = CardLoreUtility.createCardLore(blackCard);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(lore.get(0));
        lore.remove(0);
        if (!lore.isEmpty())
            meta.setLore(lore);
        icon.setItemMeta(meta);
    }
}
