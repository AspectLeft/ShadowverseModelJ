package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloralFencerTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(FloralFencer.class, FloralFencer.class).getCurrentLeader();
    }

    @Test
    void summon2() {
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);
        leader.evolve(0, null);
        assertEquals(3, leader.getAlliedFollowers().size());
    }
}