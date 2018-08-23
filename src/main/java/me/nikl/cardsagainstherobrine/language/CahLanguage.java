package me.nikl.cardsagainstherobrine.language;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;

import java.util.List;

/**
 * @author Niklas Eicker
 */
public class CahLanguage extends Language {

    public CahLanguage(CardsAgainstHerobrine plugin) {
        super(plugin);
    }

    public String getString(MessageKey key) {
        if (key.isList()) throw new IllegalArgumentException("Can't get string with list key!");
        return super.getString(key.getPath());
    }

    public List<String> getStringList(MessageKey key) {
        if (!key.isList()) throw new IllegalArgumentException("Can't get list with string key!");
        return super.getStringList(key.getPath());
    }
}
