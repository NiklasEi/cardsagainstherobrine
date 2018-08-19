package me.nikl.cardsagainstherobrine;

import me.nikl.cardsagainstherobrine.game.CahGamesManager;
import me.nikl.cardsagainstherobrine.utility.FileUtility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Niklas Eicker
 */
public class CardsAgainstHerobrine extends JavaPlugin {
    private static final boolean debug = true;
    private CahGamesManager manager;

    @Override
    public void onEnable() {
        FileUtility.copyDataFiles();
        if (!reload()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean reload() {
        manager = new CahGamesManager(this);
        return true;
    }

    public static void debug(String message) {
        if (debug) Bukkit.getLogger().info(message);
    }
}
