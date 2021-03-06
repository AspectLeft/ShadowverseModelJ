package model.card.basic.runecraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlameDestroyerTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Insight.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void cost() {
        leader.setMaximumPpAndRecover(4);
        leader.putCardIntoHand(new FlameDestroyer(leader));
        for (int i = 0; i < 4; ++i) {
            leader.playCardFromHand(0, null);
        }
        assertEquals(6, leader.getHand().get(0).getCost());
    }
}