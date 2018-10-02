package me.nikl.cardsagainstherobrine;

import org.bukkit.command.CommandSender;

/**
 * @author Niklas Eicker
 */
public enum  Permission {
    USE_COMMAND("cardsagainstherobrine.command.use");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public boolean hasPermission(CommandSender commandSender) {
        return commandSender.hasPermission(this.permission);
    }
}
