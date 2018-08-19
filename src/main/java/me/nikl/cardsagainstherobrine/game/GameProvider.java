package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;

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
        loadGamesFromDataFolder();
    }

    private void loadGamesFromDataFolder() {
        File dataFolder = new File(cahPlugin.getDataFolder() + File.separator + "data");
        File[] dataFiles = dataFolder.listFiles((file, fileName) -> file.getAbsolutePath().equals(dataFolder.getAbsolutePath()) && fileName.endsWith(".json"));
        for (File file : dataFiles) {
            GameData gameData = new GameData(file);
            gamesData.put(gameData.getName(), gameData);
            CardsAgainstHerobrine.debug("Loaded " + file.getName());
        }
    }

    public GameData getGameData(String setName) {
        return gamesData.get(setName);
    }
}
