import me.nikl.cardsagainstherobrine.language.Language;
import me.nikl.cardsagainstherobrine.language.MessageKey;
import me.nikl.cardsagainstherobrine.language.Messenger;
import me.nikl.cardsagainstherobrine.utility.FileUtility;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author Niklas Eicker
 */
public class DummyPlugin implements Plugin {
    private File folder = new File(Paths.get("src", "test", "resources").toUri());
    private FileConfiguration config;
    private Language language;
    private Logger logger = Logger.getLogger(DummyPlugin.class.getName());

    public DummyPlugin() throws IllegalAccessException, NoSuchFieldException {
        reloadConfig();
        this.language = new Language(this) {

            @Override
            public void loadDefaultLanguage() {

            }

            @Override
            public void reload() {
                messages.clear();
                lists.clear();
                this.config = plugin.getConfig();
                getLangFile();
            }

            public String getString(MessageKey key) {
                if (key.isList()) throw new IllegalArgumentException("Can't get string with list key!");
                return super.getString(key.getPath());
            }

            public List<String> getStringList(MessageKey key) {
                if (!key.isList()) throw new IllegalArgumentException("Can't get list with string key!");
                return super.getStringList(key.getPath());
            }
        };
        Field field = Messenger.class.getDeclaredField("language");
        field.setAccessible(true);
        field.set(null, language);
    }

    @Override
    public File getDataFolder() {
        return this.folder;
    }

    public Language getLanguage() {
        return this.language;
    }

    @Override
    public PluginDescriptionFile getDescription() {
        return null;
    }

    @Override
    public FileConfiguration getConfig() {
        return config;
    }

    @Override
    public InputStream getResource(String s) {
        return null;
    }

    @Override
    public void saveConfig() {

    }

    @Override
    public void saveDefaultConfig() {

    }

    @Override
    public void saveResource(String s, boolean b) {

    }

    @Override
    public void reloadConfig() {
        File con = new File(this.getDataFolder().toString() + File.separatorChar + "config.yml");
        try {
            this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(con), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PluginLoader getPluginLoader() {
        return null;
    }

    @Override
    public Server getServer() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public boolean isNaggable() {
        return false;
    }

    @Override
    public void setNaggable(boolean b) {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String s, String s1) {
        return null;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
