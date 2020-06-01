package model.card.basic.neutral;

import model.Leader;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {
    @Mock
    Leader leader;

    @Test
    public void testGoblinConstructor() {
        try {
            new Goblin(leader);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}