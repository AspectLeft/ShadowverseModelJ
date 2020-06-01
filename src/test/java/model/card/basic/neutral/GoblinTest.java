package model.card.basic.neutral;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoblinTest {
    @Test
    public void testGoblinConstructor() {
        try {
            new Goblin();
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}