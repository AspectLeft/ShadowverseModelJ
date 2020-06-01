package model.card.basic.neutral;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.neutral.token.FlameAndGlass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HarnessedGlassTest {

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(HarnessedGlass.class, HarnessedFlame.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void onStrike() {
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(6);
        opponent.playCardFromHand(0, null);
        opponent.playCardFromHand(0, null);
        opponent.endTurn();

        leader.followerStrike(0, leader.getOpponent());
        assertEquals(0, opponent.getAlliedFollowers().size());
        assertEquals(2, opponent.getCountOfShadow());
        assertEquals(19, opponent.getDefense());
    }

    @Test
    void combine() {
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        leader.playCard(new HarnessedFlame(leader), null);
        leader.endTurn();
        leader.getOpponent().endTurn();
        assertEquals(0, leader.getCountOfShadow());
        assertTrue(leader.getAlliedFollowers().get(0) instanceof FlameAndGlass);
    }
}