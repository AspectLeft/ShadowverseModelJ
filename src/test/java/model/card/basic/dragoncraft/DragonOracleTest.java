package model.card.basic.dragoncraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DragonOracleTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(DragonOracle.class, DragonOracle.class);
        leader = game.getCurrentLeader();
    }

    @Test
    void ramp() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertEquals(3, leader.getMaximumPp());
    }

    @Test
    void draw() {
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, null);
        assertEquals(4, leader.getHandSize());
    }

    @Test
    void draw_no() {
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        assertEquals(3, leader.getHandSize());
    }
}