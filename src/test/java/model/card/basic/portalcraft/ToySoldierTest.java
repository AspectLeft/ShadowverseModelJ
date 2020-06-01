package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToySoldierTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(ToySoldier.class, ToySoldier.class).getCurrentLeader();
    }

    @Test
    void effects() {
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, null);
        leader.evolve(0, null);
        leader.playCardFromHand(3, null);
        assertEquals(2, leader.getAlliedFollowers().get(1).getAttack());
    }
}