package model.card.basic.neutral.token;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlameAndGlassTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(FlameAndGlass.class, FlameAndGlass.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void onStrike() {
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, null);
        leader.recoverPp(7);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(7);
        opponent.playCardFromHand(0, null);
        opponent.followerStrike(0, leader);

        assertEquals(6, leader.getDefense());
        assertEquals(0, leader.getAlliedFollowers().size());
        assertEquals(2, leader.getCountOfShadow());
    }
}