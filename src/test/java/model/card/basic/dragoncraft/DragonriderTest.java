package model.card.basic.dragoncraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DragonriderTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(Dragonrider.class, Dragonrider.class);
        leader = game.getLeaderById(1);
    }

    @Test
    void overflow() {
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, null);
        assertEquals(4, leader.getAlliedFollowers().get(0).getAttack());
    }

    @Test
    void overflow_later() {
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        assertEquals(2, leader.getAlliedFollowers().get(0).getAttack());
        leader.endTurn();
        leader.getOpponent().endTurn();
        assertEquals(4, leader.getAlliedFollowers().get(0).getAttack());
    }
}