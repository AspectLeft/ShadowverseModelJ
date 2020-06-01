package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RazoryClawTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(RazoryClaw.class, RazoryClaw.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, leader.getEnemyEffectTargetFollowerAndLeaderChoices());
        assertEquals(18, leader.getDefense());
        assertEquals(17, leader.getOpponent().getDefense());
    }
}