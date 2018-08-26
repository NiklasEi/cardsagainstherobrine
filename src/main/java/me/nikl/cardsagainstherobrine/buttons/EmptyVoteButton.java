package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.cardsagainstherobrine.utility.CardLoreUtility;
import me.nikl.inventories.button.EmptyIconButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class EmptyVoteButton extends EmptyIconButton {
    private List<String> lore;

    public EmptyVoteButton(CahGame game, Player player) {
        this.icon = BlackCard.getBlackCardIcon();
        lore = CardLoreUtility.createAnswerLore(game.getCurrentBlackCard(), (WhiteCard[]) game.getChosenWhiteCards(player).toArray());
    }

    @Override
    public void onPreRender() {
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(lore);
        icon.setItemMeta(meta);
    }
}
