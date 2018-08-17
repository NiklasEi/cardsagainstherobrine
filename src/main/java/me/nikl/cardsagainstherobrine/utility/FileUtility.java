package me.nikl.cardsagainstherobrine.utility;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by nikl on 23.10.17.
 *
 * Utility class for language related stuff
 */
public class FileUtility {

    /**
     * Copy all data files to the plugin folder.
     *
     * This method checks for every .json in the data folder
     * whether it is already present in the plugins data folder.
     * If not it is copied.
     */
    public static void copyDataFiles() {
        URL main = CardsAgainstHerobrine.class.getResource("CardsAgainstHerobrine.class");
        try {
            JarURLConnection connection = (JarURLConnection) main.openConnection();
            JarFile jar = new JarFile(URLDecoder.decode(connection.getJarFileURL().getFile(), "UTF-8"));
            Plugin plugin = Bukkit.getPluginManager().getPlugin("CardsAgainstHerobrine");
            for (Enumeration list = jar.entries(); list.hasMoreElements(); ) {
                JarEntry entry = (JarEntry) list.nextElement();
                if (entry.getName().split("/")[0].equals("data")) {
                    String[] pathParts = entry.getName().split("/");
                    if (pathParts.length < 2 || !entry.getName().endsWith(".json")) {
                        continue;
                    }
                    File file = new File(plugin.getDataFolder().toString() + File.separatorChar + entry.getName());
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        plugin.saveResource(entry.getName(), false);
                    }
                }
            }
            jar.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
