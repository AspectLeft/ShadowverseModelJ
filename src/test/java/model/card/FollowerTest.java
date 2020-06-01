package model.card;

import model.Game;
import model.Leader;
import model.card.basic.swordcraft.Quickblader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FollowerTest {
    Leader leader, targetLeader;

    @BeforeEach
    void setup() {
        Game game = CardBaseTest.setUpWithDecksOf10(Quickblader.class, Quickblader.class);
        leader = game.getCurrentLeader();
        targetLeader = leader.getOpponent();
    }

    @Test
    void strike_leader() {
        leader.playCardFromHand(0, null);

        assertDoesNotThrow(() -> {
            leader.followerStrike(0, targetLeader);
            assertEquals(19, targetLeader.getDefense());
        });
    }

    @Test
    void strike_follower() {
        leader.playCardFromHand(0, null);
        leader.endTurn();
        targetLeader.playCardFromHand(0, null);
        targetLeader.followerStrike(0, leader.getArea()[0]);

        assertEquals(1, leader.getCountOfShadow());
        assertEquals(1, targetLeader.getCountOfShadow());
        assertNull(leader.getArea()[0]);
        assertNull(targetLeader.getArea()[0]);
    }
}