package me.nikl.cardsagainstherobrine.language;

/**
 * @author Niklas Eicker
 */
public enum MessageKey {
    PREFIX("prefix"),
    NAME("name"),
    GAME_CARD_LORES_BLACK_TEXT("game.cardLores.blackCards.text"),
    GAME_CARD_LORES_BLACK_PLACEHOLDER("game.cardLores.blackCards.placeholder"),
    GAME_CARD_LORES_BLACK_ANSWER("game.cardLores.blackCards.answer"),
    GAME_CARD_LORES_WHITE_TEXT("game.cardLores.whiteCards.text"),
    GAME_PRE_TITLE("game.inventorytitle.pre"),
    GAME_CHOOSE_TITLE("game.inventorytitle.choose"),
    GAME_VOTE_TITLE("game.inventorytitle.vote"),
    GAME_BUTTON_LORE("game.button.lore", true);


    private String path;
    private boolean list;
    MessageKey(String path, boolean list){
        this.path = path;
        this.list = list;
    }

    MessageKey(String path){
        this(path, false);
    }

    public String getPath(){
        return this.path;
    }

    public boolean isList(){
        return this.list;
    }
}
