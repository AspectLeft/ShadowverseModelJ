package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DemonicStormTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(DemonicStorm.class, DemonicStorm.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.summon(new Fairy(leader));
        leader.getOpponent().summon(new Fairy(leader.getOpponent()));
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);

        assertEquals(17, leader.getDefense());
        assertTrue(leader.getAlliedFollowers().isEmpty());
        assertEquals(17, leader.getOpponent().getDefense());
        assertTrue(leader.getOpponent().getAlliedFollowers().isEmpty());
    }
}