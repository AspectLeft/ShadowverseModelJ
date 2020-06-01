package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DarkGeneralTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(DarkGeneral.class, DarkGeneral.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setDefense(10);
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);

        assertTrue(leader.getAlliedFollowers().get(0).canAttackLeader());
    }
}