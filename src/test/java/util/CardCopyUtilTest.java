package util;

import model.Leader;
import model.card.CardBase;
import model.card.basic.neutral.Goblin;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CardCopyUtilTest {
    @Mock
    Leader leader;

    @Test
    void copy() {
        Goblin goblin = new Goblin(leader);

        assertDoesNotThrow(() -> {
            CardBase card;
            card = CardCopyUtil.copy(leader, goblin);
            assertTrue(card instanceof Goblin);
        });
    }

    @Test
    void createCardInstance() {
        assertDoesNotThrow(() -> {
            CardBase card;
            card = CardCopyUtil.createCardInstance(leader, Goblin.class);
            assertTrue(card instanceof Goblin);
        });
    }
}