package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.cardsagainstherobrine.utility.CardLoreUtility;
import me.nikl.cardsagainstherobrine.utility.ItemStackUtility;
import me.nikl.inventories.button.EasyButton;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

/**
 * @author Niklas Eicker
 */
public class VoteButton extends EasyButton {
    private CahGame game;
    private List<String> lore;
    private UUID whiteCardOwner;

    public VoteButton(CahGame game, Player whiteCardOwner) {
        this.game = game;
        this.whiteCardOwner = whiteCardOwner.getUniqueId();
        this.icon = BlackCard.getBlackCardIcon();
        lore = CardLoreUtility.createAnswerLore(game.getCurrentBlackCard(), (WhiteCard[]) game.getChosenWhiteCards(whiteCardOwner).toArray());
    }

    @Override
    public boolean onClick(InventoryClickEvent inventoryClickEvent) {
        game.voteClick(this, inventoryClickEvent.getWhoClicked().getUniqueId());
        return true;
    }

    @Override
    public void onPreRender() {
        ItemMeta meta = icon.getItemMeta();
        meta.setLore(lore);
        icon.setItemMeta(meta);
    }

    @Override
    public void onAfterRender() {
        // nothing to do
    }

    public void unselect() {
        updateIcon(ItemStackUtility.removeGlow(icon));
    }

    public void select() {
        updateIcon(ItemStackUtility.addGlow(icon));
    }
}
