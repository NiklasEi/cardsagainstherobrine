package me.nikl.cardsagainstherobrine.utility;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.Card;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Niklas Eicker
 */
public class CardLoreUtility {
    private static long charsPerLine = 30;

    public static List<String> createAnswerLore(BlackCard blackCard, WhiteCard... whiteCards) {
        if (blackCard.getPick() != whiteCards.length) throw new IllegalArgumentException("Unexpected number of white cards!");
        String answerString = blackCard.getText();
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
        long charCount = 0;
        StringBuilder lineBuilder = new StringBuilder();
        for (String word : words) {
            if (charCount > charsPerLine) {
                charCount = 0;
                toReturn.add(lineBuilder.toString());
                lineBuilder = new StringBuilder();
                lineBuilder.append(word);
                continue;
            }
            lineBuilder.append(charCount==0?"":" " + word);
            charCount += word.toCharArray().length + 1;
        }
        toReturn.add(lineBuilder.toString());
        return toReturn;
    }

    public static List<String> createCardLore(Card card) {
        return formatLore(card.getText());
    }
}
