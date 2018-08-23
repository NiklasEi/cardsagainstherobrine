package me.nikl.cardsagainstherobrine.utility;

import me.nikl.nmsutilities.NmsFactory;
import org.bukkit.entity.Player;

/**
 * @author Niklas Eicker
 */
public class InventoryUtilities {
    public static void updateInventoryTitle(Player player, String title) {
        NmsFactory.getNmsUtility().updateInventoryTitle(player, title);
    }
}
