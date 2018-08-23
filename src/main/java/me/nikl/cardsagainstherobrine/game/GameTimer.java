package me.nikl.cardsagainstherobrine.game;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Niklas Eicker
 */
public class GameTimer extends BukkitRunnable {
    private Set<CahGame> games = new HashSet<>();

    @Override
    public void run() {
        games.forEach((game) -> game.tick());
    }

    public void registerGame(CahGame game) {
        games.add(game);
    }

    public void removeGame(CahGame game) {
        games.remove(game);
    }
}
