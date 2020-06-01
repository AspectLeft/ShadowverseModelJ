package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CrimsonPurgeTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, CrimsonPurge.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void effect() {
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(4);
        opponent.playCardFromHand(0, opponent.getHand().get(0).getChoices().get(0));

        assertTrue(leader.getAlliedFollowers().isEmpty());
        assertEquals(18, opponent.getDefense());
    }

}