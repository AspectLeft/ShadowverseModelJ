package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.swordcraft.Quickblader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SweetfangVampireTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(SweetfangVampire.class, Quickblader.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void drain() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.playCardFromHand(0, null);
        opponent.followerStrike(0, leader);
        opponent.endTurn();

        leader.followerStrike(0, opponent);
        assertEquals(20, leader.getDefense());
    }
}