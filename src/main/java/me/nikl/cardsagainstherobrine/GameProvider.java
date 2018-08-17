package me.nikl.cardsagainstherobrine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Niklas Eicker
 */
public class GameProvider {
    private CardsAgainstHerobrine cahPlugin;
    private Map<String, GameData> gamesData = new HashMap<>();

    public GameProvider(CardsAgainstHerobrine cahPlugin) {
        this.cahPlugin = cahPlugin;
        File dataFile = new File(cahPlugin.getDataFolder() + File.separator + "data" + File.separator + "base.json");
        GameData baseGame = new GameData(dataFile);
    }
}
