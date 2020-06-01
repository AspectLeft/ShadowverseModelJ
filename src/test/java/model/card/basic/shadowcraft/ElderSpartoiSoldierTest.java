package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ElderSpartoiSoldierTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(ElderSpartoiSoldier.class, ElderSpartoiSoldier.class)
                .getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);
        assertEquals(2, leader.getCountOfShadow());
    }
}