package me.nikl.cardsagainstherobrine.cards;

import org.bukkit.inventory.ItemStack;

/**
 * @author Niklas Eicker
 */
public class WhiteCard implements Card {
    private static ItemStack whiteCardIcon;
    private String text;

    public WhiteCard(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    public static void setWhiteCardIcon(ItemStack whiteCardIcon) {
        WhiteCard.whiteCardIcon = whiteCardIcon;
    }

    public static ItemStack getWhiteCardIcon() {
        return WhiteCard.whiteCardIcon.clone();
    }
}
