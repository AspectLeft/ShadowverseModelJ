package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoanWingedNexxTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(RoanWingedNexx.class, RoanWingedNexx.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void evolve_empty() {
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(4);
        opponent.playCardFromHand(0, null);
        assertNull(opponent.getAlliedFollowers().get(0).getEvolveChoices());
        opponent.evolve(0, null);
    }

    @Test
    void evolve() {
        opponent.summon(new Fairy(opponent));
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);
        leader.evolve(0, leader.getAlliedFollowers().get(0).getEvolveChoices().get(0));

        assertTrue(opponent.getAlliedFollowers().isEmpty());
    }

}