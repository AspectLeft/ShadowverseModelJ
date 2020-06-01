package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreantTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        leader.playCard(new Treant(leader), null);
        assertEquals(6, leader.getAlliedFollowers().get(2).getAttack());
        assertEquals(6, leader.getAlliedFollowers().get(2).getDefense());
    }

}