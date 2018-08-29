package me.nikl.cardsagainstherobrine.utility;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.Card;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.language.MessageKey;
import me.nikl.cardsagainstherobrine.language.Messenger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Niklas Eicker
 */
public class CardLoreUtility {
    private static long charsPerLine = 25;

    public static List<String> createAnswerLore(BlackCard blackCard, WhiteCard... whiteCards) {
        if (blackCard.getPick() != whiteCards.length) throw new IllegalArgumentException("Unexpected number of white cards!");
        String answerString = blackCard.getText();
        answerString = Messenger.getString(MessageKey.GAME_CARD_LORES_BLACK_TEXT) + answerString;
        answerString = answerString.replace("_", Messenger.getString(MessageKey.GAME_CARD_LORES_BLACK_ANSWER));
        int i = 0;
        while (answerString.contains("_")) {
            answerString.replaceFirst("_", whiteCards[i].getText());
            i++;
        }

        List<String> answerLore = formatLore(answerString);

        for (int j = i; j < whiteCards.length; j++) {
            String whiteText = whiteCards[j].getText();
            List<String> whiteLore = formatLore(whiteText);
            answerLore.add("");
            answerLore.addAll(whiteLore);
        }
        return answerLore;
    }

    private static List<String> formatLore(String answerString) {
        // ToDo: make some nice breaks in the lore (color support over line breaks!)
        List<String> toReturn = new ArrayList<>();
        String[] words = answerString.split(" ");
        StringBuilder lineBuilder = new StringBuilder();
        for (String word : words) {
            if (lineBuilder.length() > charsPerLine) {
                String lastLine = lineBuilder.toString();
                toReturn.add(lastLine);
                lineBuilder = new StringBuilder();
                lineBuilder.append(ChatColor.getLastColors(lastLine) + word + " ");
                continue;
            }
            lineBuilder.append(word + " ");
        }
        if (lineBuilder.length() != 0) toReturn.add(lineBuilder.toString());
        Bukkit.getLogger().info("Formatted '" + answerString + "' to: " + toReturn.toString());
        return toReturn;
    }

    public static List<String> createCardLore(Card card) {
        String text = card.getText();
        text = (card instanceof BlackCard ? Messenger.getString(MessageKey.GAME_CARD_LORES_BLACK_TEXT)
                + text.replace("_", Messenger.getString(MessageKey.GAME_CARD_LORES_BLACK_TEXT) + "_")
                : Messenger.getString(MessageKey.GAME_CARD_LORES_WHITE_TEXT) + text);
        return formatLore(text);
    }
}
