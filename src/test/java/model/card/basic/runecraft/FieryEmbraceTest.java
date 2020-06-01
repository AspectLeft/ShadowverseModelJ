package model.card.basic.runecraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieryEmbraceTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Insight.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.getOpponent().summon(new WaterFairy(leader.getOpponent()));

        FieryEmbrace fieryEmbrace = new FieryEmbrace(leader);
        leader.setMaximumPpAndRecover(8);
        leader.playCard(fieryEmbrace, fieryEmbrace.getChoices().get(0));

        assertTrue(leader.getOpponent().getAlliedFollowers().isEmpty());
    }

    @Test
    void cost() {
        leader.setMaximumPpAndRecover(4);
        leader.putCardIntoHand(new FieryEmbrace(leader));
        for (int i = 0; i < 4; ++i) {
            leader.playCardFromHand(0, null);
        }
        assertEquals(4, leader.getHand().get(0).getCost());
    }
}