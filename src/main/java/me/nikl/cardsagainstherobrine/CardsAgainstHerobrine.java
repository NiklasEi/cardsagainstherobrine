package me.nikl.cardsagainstherobrine;

import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.commands.CahCommand;
import me.nikl.cardsagainstherobrine.game.CahGamesManager;
import me.nikl.cardsagainstherobrine.language.CahLanguage;
import me.nikl.cardsagainstherobrine.language.Language;
import me.nikl.cardsagainstherobrine.language.Messenger;
import me.nikl.cardsagainstherobrine.utility.FileUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author Niklas Eicker
 */
public class CardsAgainstHerobrine extends JavaPlugin {
    private static final boolean debug = true;
    private FileConfiguration config;
    private CahGamesManager manager;
    private CahLanguage language;

    @Override
    public void onEnable() {
        FileUtility.copyDataFiles();
        if (!reload()) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
    }

    private boolean reload() {
        if(!reloadConfiguration()){
            getLogger().severe(" Failed to load config file!");
            return false;
        }
        if (this.language == null) {
            this.language = new CahLanguage(this);
        } else {
            this.language.reload();
        }
        // ToDo: load icons
        WhiteCard.setWhiteCardIcon(new ItemStack(Material.PAPER));
        BlackCard.setBlackCardIcon(new ItemStack(Material.BOOK));
        manager = new CahGamesManager(this);
        getCommand("cardsagainstherobrine").setExecutor(new CahCommand(this));
        return true;
    }

    public boolean reloadConfiguration(){
        // save the default configuration file if the file does not exist
        File con = new File(this.getDataFolder().toString() + File.separatorChar + "config.yml");
        if(!con.exists()){
            this.saveResource("config.yml", false);
        }

        // reload config
        try {
            this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(con), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void debug(String message) {
        if (debug) Bukkit.getLogger().info(message);
    }

    public CahGamesManager getManager() {
        return manager;
    }

    public CahLanguage getLanguage() {
        return language;
    }

    @Override
    public FileConfiguration getConfig(){
        return this.config;
    }
}
