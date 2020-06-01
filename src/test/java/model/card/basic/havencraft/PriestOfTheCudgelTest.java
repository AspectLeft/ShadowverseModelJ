package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.neutral.Goliath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriestOfTheCudgelTest {

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, PriestOfTheCudgel.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void evolve() {
        leader.setMaximumPpAndRecover(5);
        leader.playCard(new Goliath(leader), null);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(4);
        opponent.playCardFromHand(0, null);
        opponent.evolve(0, opponent.getEvolveChoice(0).get(0));

        assertEquals(0, leader.getCountOfShadow());
        assertTrue(leader.getAlliedFollowers().get(0) instanceof Goliath);
    }
}