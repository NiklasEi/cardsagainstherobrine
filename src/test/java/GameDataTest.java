import me.nikl.cardsagainstherobrine.cards.BlackCard;
import me.nikl.cardsagainstherobrine.game.GameData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Niklas Eicker
 */
public class GameDataTest {
    private File testFile;
    private GameData testData;
    private int testSize = 20;

    @Before
    public void setUp() {
        // contains 90 black cards and 460 white cards
        testFile = new File(Paths.get("src", "test", "resources", "base.json").toUri());
        testData = new GameData(testFile);
    }

    @Test
    public void checkLoadedData() {
        Assert.assertNotNull(testData);
        List<BlackCard> blackCards = testData.getNRandomBlackCards(testSize);
        Assert.assertEquals(blackCards.size(), testSize);
        Assert.assertEquals(testData.getWhiteCards().size(), 460);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkBlackCards() {
        // try to get more black cards then in the deck
        testData.getNRandomBlackCards(91);
    }
}
