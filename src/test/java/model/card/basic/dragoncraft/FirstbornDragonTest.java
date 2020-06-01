package model.card.basic.dragoncraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstbornDragonTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(FirstbornDragon.class, FirstbornDragon.class);
        leader = game.getLeaderById(1);
    }

    @Test
    void overflow() {
        leader.setMaximumPpAndRecover(7);
        Follower follower = (Follower)leader.playCardFromHand(0, null);
        assertTrue(follower.hasWard());
    }

    @Test
    void overflow_later() {
        leader.setMaximumPpAndRecover(6);
        Follower follower = (Follower)leader.playCardFromHand(0, null);
        assertFalse(follower.hasWard());
        leader.endTurn();
        leader.getOpponent().endTurn();
        assertTrue(follower.hasWard());
    }

}