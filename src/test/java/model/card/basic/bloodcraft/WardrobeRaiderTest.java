package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WardrobeRaiderTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, WardrobeRaider.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void evolve() {
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(4);
        opponent.setDefense(18);
        opponent.playCardFromHand(0, null);

        opponent.evolve(0, opponent.getEvolveChoice(0).get(0));

        assertTrue(leader.getAlliedFollowers().isEmpty());
        assertEquals(20, opponent.getDefense());
    }
}