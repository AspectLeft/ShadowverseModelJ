package model.card.basic.shadowcraft.token;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GhostTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Ghost.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void storm() {
        Follower follower = (Follower) leader.playCardFromHand(0, null);
        assertTrue(follower.canAttackLeader());
    }

    @Test
    void leavingIsBanishing() {
        leader.endTurn();
        Follower target = (Follower) leader.getOpponent().playCardFromHand(0, null);
        leader.getOpponent().endTurn();

        leader.playCardFromHand(0, null);
        leader.followerStrike(0, target);
        assertEquals(0, leader.getCountOfShadow());
    }

    @Test
    void autoBanishing() {
        leader.playCardFromHand(0, null);
        leader.endTurn();

        assertTrue(leader.getAlliedFollowers().isEmpty());
        assertEquals(0, leader.getCountOfShadow());
    }

}