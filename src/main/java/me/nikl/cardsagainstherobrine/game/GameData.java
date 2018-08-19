package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Niklas Eicker
 *
 * This class loads game data from a JSON file and acts as a provider of the loaded data.
 */
public class GameData {
    private List<BlackCard> blackCards;
    private List<WhiteCard> whiteCards;
    private String name;
    private String iconString;

    public GameData(File file) {
        loadGameDataFromJSON(file);
        Bukkit.getLogger().info("Got " + blackCards.size() + " black cards and " + whiteCards.size() + " white cards.");
    }

    private void loadGameDataFromJSON(File file) {
        JSONParser parser = new JSONParser();
        blackCards = new ArrayList<>();
        whiteCards = new ArrayList<>();
        try {
            Object object = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) object;
            JSONArray blackCards = (JSONArray) jsonObject.get("blackCards");
            JSONArray whiteCards = (JSONArray) jsonObject.get("whiteCards");

            JSONArray order = (JSONArray) jsonObject.get("order");
            for (int i = 0; i < order.size(); i++) {
                String orderName = (String) order.get(i);
                JSONObject setObject = (JSONObject) jsonObject.get(orderName);
                name = (String) setObject.get("name");
                iconString = (String) setObject.get("icon");
                Bukkit.getLogger().info("Loading set: " + name);
                Bukkit.getLogger().info("Icon: " + iconString);
                JSONArray blackCardsIndices = (JSONArray) setObject.get("black");
                JSONArray whiteCardsIndices = (JSONArray) setObject.get("white");

                JSONObject cardObject;
                for (int j = 0; j < blackCardsIndices.size(); j++) {
                    cardObject = (JSONObject) blackCards.get(((Long) blackCardsIndices.get(i)).intValue());
                    this.blackCards.add(new BlackCard((String) cardObject.get("text"), ((Long) cardObject.get("pick")).intValue()));
                }

                for (int j = 0; j < whiteCardsIndices.size(); j++) {
                    this.whiteCards.add(new WhiteCard((String) whiteCards.get(((Long) whiteCardsIndices.get(i)).intValue())));
                }
            }
            Collections.shuffle(this.blackCards);
            Collections.shuffle(this.whiteCards);
            this.blackCards = Collections.unmodifiableList(this.blackCards);
            this.whiteCards = Collections.unmodifiableList(this.whiteCards);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<BlackCard> getBlackCards() {
        return this.blackCards;
    }

    public List<WhiteCard> getWhiteCards() {
        return this.whiteCards;
    }

    public String getName() {
        return this.name;
    }
}
