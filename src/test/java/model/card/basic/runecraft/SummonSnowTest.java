package model.card.basic.runecraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SummonSnowTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(SummonSnow.class, SummonSnow.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.setMaximumPpAndRecover(3);
        SummonSnow summonSnow = (SummonSnow) leader.getHand().get(0);
        summonSnow.increaseSpellboost(2);
        leader.playCardFromHand(0, null);

        assertEquals(3, leader.getAlliedFollowers().size());
    }
}