package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ElfTrackerTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, ElfTracker.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(6);
        opponent.playCardFromHand(0, null);

        assertTrue(leader.getAlliedFollowers().isEmpty());
    }

}