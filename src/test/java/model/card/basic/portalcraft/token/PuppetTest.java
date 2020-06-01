package model.card.basic.portalcraft.token;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PuppetTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Puppet.class, Puppet.class).getCurrentLeader();
    }

    @Test
    void selfDestroy() {
        leader.playCardFromHand(0, null);
        leader.endTurn();
        leader.getOpponent().endTurn();

        assertEquals(1, leader.getCountOfShadow());
        assertEquals(0, leader.getAlliedFollowers().size());
    }
}