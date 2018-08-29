import me.nikl.cardsagainstherobrine.language.Language;
import me.nikl.cardsagainstherobrine.language.MessageKey;
import me.nikl.cardsagainstherobrine.language.Messenger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.UUID;

/**
 * @author Niklas Eicker
 */
public class LanguageTest {
    private Language language;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        language = new DummyPlugin().getLanguage();
    }

    @Test
    public void checkMessageCacheIndependence() {
        String unique = UUID.randomUUID().toString();
        Messenger.createList(MessageKey.GAME_BUTTON_LORE, Collections.singletonMap("%", unique));
        for (String string : language.getStringList(MessageKey.GAME_BUTTON_LORE.getPath())) {
            Assert.assertFalse(string.contains(unique));
        }
    }
}
