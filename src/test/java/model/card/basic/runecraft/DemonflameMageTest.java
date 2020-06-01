package model.card.basic.runecraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DemonflameMageTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, DemonflameMage.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void evolve() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(4);
        opponent.playCardFromHand(0, null);
        opponent.evolve(0, null);
        assertTrue(leader.getAlliedFollowers().isEmpty());
    }

}