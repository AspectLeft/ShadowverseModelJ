package model.card.basic.portalcraft.token;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RadiantArtifactTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void lastWords_IsTurn_Empty() {
        leader.setMaximumPpAndRecover(5);
        leader.playCard(new RadiantArtifact(leader), null);
        leader.getAlliedFollowers().get(0).destroy();
        leader.getGame().settleAllEffects();

        assertEquals(4, leader.getHandSize());
    }

    @Test
    void lastWords_IsTurn_Retrieve() {
        leader.setMaximumPpAndRecover(5);
        leader.putCardsIntoDeck(Collections.singletonList(new AnalyzingArtifact(leader)));
        leader.playCard(new RadiantArtifact(leader), null);
        leader.getAlliedFollowers().get(0).destroy();
        leader.getGame().settleAllEffects();

        assertEquals(5, leader.getHandSize());
        assertTrue(leader.getHand().get(4) instanceof AnalyzingArtifact);
    }

    @Test
    void lastWords_IsNotTurn_Draw() {
        leader.setMaximumPpAndRecover(5);
        leader.playCard(new RadiantArtifact(leader), null);

        leader.endTurn();

        leader.getAlliedFollowers().get(0).destroy();
        leader.getGame().settleAllEffects();

        assertEquals(5, leader.getHandSize());
    }
}