package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrazedExecutionerTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(CrazedExecutioner.class, CrazedExecutioner.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, null);
        assertEquals(18, leader.getDefense());
    }
}