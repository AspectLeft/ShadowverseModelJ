package model.card.basic.portalcraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionCutTest {

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, DimensionCut.class);
        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void effect() {
        WaterFairy waterFairy = (WaterFairy) leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        opponent.playCardFromHand(0, opponent.getEnemyEffectTargetFollowerChoices());
        assertEquals(-2, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertNull(leader.getArea()[0]);
    }

    @Test
    void effect_resonance() {
        WaterFairy waterFairy = (WaterFairy) leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        opponent.drawCard(1);
        opponent.playCardFromHand(0, opponent.getEnemyEffectTargetFollowerChoices());
        assertEquals(-4, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertNull(leader.getArea()[0]);
    }


}