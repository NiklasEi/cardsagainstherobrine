package me.nikl.cardsagainstherobrine.cards;

import org.bukkit.inventory.ItemStack;

/**
 * @author Niklas Eicker
 */
public class BlackCard implements Card {
    private static ItemStack blackCardIcon;
    private int pick;
    private String text;

    public BlackCard(String text, int pick) {
        this.text = text.replace("&trade;", "™");
        this.pick = pick;
    }

    @Override
    public String getText() {
        return text;
    }

    public int getPick() {
        return pick;
    }

    public static void setBlackCardIcon(ItemStack blackCardIcon) {
        BlackCard.blackCardIcon = blackCardIcon;
    }

    public static ItemStack getBlackCardIcon() {
        return BlackCard.blackCardIcon.clone();
    }
}
