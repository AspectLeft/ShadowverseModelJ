package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BloodPactTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(BloodPact.class, BloodPact.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertEquals(18, leader.getDefense());
        assertEquals(5, leader.getHandSize());
    }
}