package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.neutral.Goliath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcolytesLightTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(AcolytesLight.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.setDefense(10);
        leader.getOpponent().summon(new Goliath(leader.getOpponent()));
        leader.setMaximumPpAndRecover(5);
        leader.playCardFromHand(0, leader.getPlayCardChoice(0).get(0));

        assertEquals(14, leader.getDefense());
        assertEquals(0, leader.getOpponent().getCountOfShadow());
        assertTrue(leader.getAlliedFollowers().isEmpty());
    }

}