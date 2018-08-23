package me.nikl.cardsagainstherobrine.game;

import me.nikl.cardsagainstherobrine.buttons.BlackCardButton;
import me.nikl.cardsagainstherobrine.buttons.CahGameButton;
import me.nikl.cardsagainstherobrine.buttons.WhiteCardButton;
import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.cards.WhiteCard;
import me.nikl.cardsagainstherobrine.language.CahLanguage;
import me.nikl.cardsagainstherobrine.language.MessageKey;
import me.nikl.cardsagainstherobrine.language.Messenger;
import me.nikl.inventories.Inventory;
import me.nikl.inventories.button.EmptyIconButton;
import me.nikl.inventories.inventory.EasyInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Niklas Eicker
 */
public class CahGame {
    private CahGamesManager manager;
    private List<Player> players = new ArrayList<>();
    private Map<UUID, List<WhiteCard>> playerCards = new HashMap<>();
    private Map<UUID, Inventory> playerInventories = new HashMap<>();
    private Map<UUID, List<WhiteCardButton>> selectedPlayerCards = new HashMap<>();
    private GameData gameData;
    private GameRules gameRules;
    private int maxRounds = 10;
    private int currentRound = -1;
    private List<BlackCard> blackCards;
    private List<WhiteCard> whiteCards;
    private int whiteCardsPerTurn = 5;
    private GameState gameState = GameState.PRE_GAME;
    private CahGameButton gameButton;
    private long stampForNextPhase = 0;
    private CahLanguage language;
    private Map<String, String> gameContext = new HashMap<>();

    public CahGame(GameRules gameRules, CahGamesManager manager) {
        this.manager = manager;
        this.gameData = gameRules.getGameData();
        this.gameRules = gameRules;
        this.language = manager.getPlugin().getLanguage();
        loadContext();
        prepareGame();
    }

    private void prepareGame() {
        gameButton = new CahGameButton(this, createGameIcon());
        manager.getMainGui().addButtons(gameButton);
        updateGameButton();
        prepareDeck();
    }

    private ItemStack createGameIcon() {
        return new ItemStack(Material.BOOK);
    }

    private void prepareDeck() {
        whiteCards = new ArrayList<>(gameData.getWhiteCards());
        Collections.shuffle(whiteCards);
        this.blackCards = gameData.getNRandomBlackCards(maxRounds);
    }

    public void join(Player whoClicked) {
        if (this.gameState != GameState.PRE_GAME) throw new IllegalStateException("The game is already running...");
        preparePlayer(whoClicked.getUniqueId());
        playerInventories.get(whoClicked.getUniqueId()).open(whoClicked);
        players.add(whoClicked);
        if (players.size() == gameRules.getNumberOfPlayers()) {
            this.gameState = GameState.ROUND_SELECT;
            stampForNextPhase = System.currentTimeMillis() + gameRules.getSecondsToPick() * 1000;
            nextRound();
        }
        updateGameButton();
    }

    private void preparePlayer(UUID uniqueId) {
        prepareInventory(uniqueId);
        playerCards.put(uniqueId, new ArrayList<>(whiteCards.subList(whiteCardsPerTurn*(players.size()), whiteCardsPerTurn*(players.size() + 1))));
    }

    private void prepareInventory(UUID uniqueId) {
        EasyInventory playerInventory = new EasyInventory(CahGamesManager.GROUP_MAIN, 54, "Game");
        playerInventories.putIfAbsent(uniqueId, playerInventory);
        playerInventory.addButton(8, new EmptyIconButton(new ItemStack(Material.CLOCK)));
    }

    private void nextRound() {
        currentRound ++;
        fillInventoriesForRound();
    }

    private void fillInventoriesForRound() {
        for (Player player : players) {
            fillInventoryForRound(player.getUniqueId());
        }
    }

    private void fillInventoryForRound(UUID uniqueId) {
        Inventory inventory = playerInventories.get(uniqueId);
        inventory.addButton(4, new BlackCardButton(blackCards.get(currentRound), this));
        for (int i = 0; i < whiteCardsPerTurn; i++)
            inventory.addButton(18 + i, new WhiteCardButton(whiteCards.get(i), this));
    }

    public boolean selectWhiteCard(WhiteCardButton whiteCardButton, Player whoClicked) {
        if (!selectedPlayerCards.keySet().contains(whoClicked.getUniqueId())) selectedPlayerCards.put(whoClicked.getUniqueId(), new ArrayList<>());
        List<WhiteCardButton> selectedCards = selectedPlayerCards.get(whoClicked.getUniqueId());
        whiteCardButton.select();
        selectedCards.add(0, whiteCardButton);
        if (selectedCards.size() > blackCards.get(currentRound).getPick()) {
            selectedCards.remove(blackCards.get(currentRound).getPick()).select();
        }
        selectedPlayerCards.put(whoClicked.getUniqueId(), selectedCards);
        return true;
    }

    public void tick() {
        if (gameState != GameState.ROUND_SELECT && gameState != GameState.ROUND_VOTE) return;
        long current = System.currentTimeMillis();
        if (current < stampForNextPhase) return;
        nextPhase();
    }

    private void nextPhase() {
        if (gameState == GameState.ROUND_VOTE) {
            nextRound();
            return;
        }
        startVoting();
    }

    private void startVoting() {
    }

    public void leftGame(Player player) {
        player.setCooldown(Material.CLOCK, 0);
    }

    private void updateGameButton() {
        updateGameContext();
        gameButton.updateLore(Messenger.createList(MessageKey.GAMEBUTTON_LORE, gameContext));
    }

    private void loadContext() {
        gameContext.put("%maxPlayers%", String.valueOf(gameRules.getNumberOfPlayers()));
        updateGameContext();
    }

    private void updateGameContext() {
        gameContext.put("%players%", String.valueOf(players.size()));
        gameContext.put("%status%", gameState.name());
    }
}
