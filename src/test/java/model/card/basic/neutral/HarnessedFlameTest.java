package model.card.basic.neutral;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.neutral.token.FlameAndGlass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HarnessedFlameTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(HarnessedFlame.class, HarnessedFlame.class).getCurrentLeader();
    }

    @Test
    void onStrike() {
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, null);
        leader.endTurn();
        leader.getOpponent().endTurn();
        leader.followerStrike(0, leader.getOpponent());
        assertEquals(16, leader.getOpponent().getDefense());
    }

    @Test
    void combine() {
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        leader.playCard(new HarnessedGlass(leader), null);
        leader.endTurn();
        leader.getOpponent().endTurn();
        assertEquals(0, leader.getCountOfShadow());
        assertTrue(leader.getAlliedFollowers().get(0) instanceof FlameAndGlass);
    }
}