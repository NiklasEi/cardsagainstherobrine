package me.nikl.cardsagainstherobrine.buttons;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.game.CahGame;
import me.nikl.cardsagainstherobrine.utility.CardLoreUtility;
import me.nikl.inventories.button.EmptyIconButton;

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
    }

    @Override
    public void onAfterRender() {
        updateLore(CardLoreUtility.createCardLore(blackCard));
    }
}
