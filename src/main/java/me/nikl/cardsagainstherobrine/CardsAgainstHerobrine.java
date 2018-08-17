package me.nikl.cardsagainstherobrine;

import me.nikl.cardsagainstherobrine.utility.FileUtility;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Niklas Eicker
 */
public class CardsAgainstHerobrine extends JavaPlugin {

    @Override
    public void onEnable() {
        FileUtility.copyDataFiles();
        new GameProvider(this);
    }
}
