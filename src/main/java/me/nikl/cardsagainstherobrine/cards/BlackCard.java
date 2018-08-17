package me.nikl.cardsagainstherobrine.cards;

/**
 * @author Niklas Eicker
 */
public class BlackCard {
    private int pick;
    private String text;

    public BlackCard(String text, int pick) {
        this.text = text;
        this.pick = pick;
    }

    public String getText() {
        return text;
    }

    public int getPick() {
        return pick;
    }
}
