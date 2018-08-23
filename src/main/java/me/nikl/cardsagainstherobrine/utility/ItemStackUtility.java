package me.nikl.cardsagainstherobrine.utility;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @author Niklas Eicker
 */
public class ItemStackUtility {
    public  static ItemStack addGlow(ItemStack item) {
        if (item == null) return null;
        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public  static ItemStack removeGlow(ItemStack item) {
        if (item == null) return null;
        ItemMeta meta = item.getItemMeta();
        for (Enchantment enchantment : Enchantment.values()) {
            meta.removeEnchant(enchantment);
        }
        item.setItemMeta(meta);
        return item;
    }
}
