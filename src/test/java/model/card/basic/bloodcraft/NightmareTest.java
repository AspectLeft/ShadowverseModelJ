package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NightmareTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Nightmare.class, Nightmare.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.takeDamage(10);
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertEquals(4, leader.getAlliedFollowers().get(0).getAttack());
    }
}