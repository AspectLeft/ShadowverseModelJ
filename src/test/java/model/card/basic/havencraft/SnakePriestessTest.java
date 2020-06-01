package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import model.effect.Ward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SnakePriestessTest {

    Leader leader;
    
    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(SnakePriestess.class, SnakePriestess.class).getCurrentLeader();
    }
    
    @Test
    void ward() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertTrue(leader.getAlliedFollowers().get(0).collectEffects().stream()
                .anyMatch(effect -> effect instanceof Ward));
    }
}