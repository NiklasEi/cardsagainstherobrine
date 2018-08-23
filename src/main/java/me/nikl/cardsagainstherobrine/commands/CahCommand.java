package me.nikl.cardsagainstherobrine.commands;

import me.nikl.cardsagainstherobrine.CardsAgainstHerobrine;
import me.nikl.inventories.inventory.EasyInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Niklas Eicker
 */
public class CahCommand implements CommandExecutor {
    private CardsAgainstHerobrine cardsAgainstHerobrine;
    private EasyInventory mainGui;

    public CahCommand(CardsAgainstHerobrine cardsAgainstHerobrine) {
        this.cardsAgainstHerobrine = cardsAgainstHerobrine;
        mainGui = cardsAgainstHerobrine.getManager().getMainGui();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        mainGui.open(player);
        return true;
    }
}
