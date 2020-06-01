package model.card.basic.runecraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindBlastTest {
    Leader leader, opponent;
    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(Insight.class, WaterFairy.class);
        leader = game.getLeaderById(1);
        opponent = game.getLeaderById(2);
    }

    @Test
    void spellboost() {
        leader.endTurn();
        WaterFairy waterFairy = (WaterFairy) opponent.playCardFromHand(0, null);
        opponent.endTurn();

        leader.setMaximumPpAndRecover(7);
        leader.putCardIntoHand(new WindBlast(leader));
        for (int i = 0; i < 5; ++i) {
            leader.playCardFromHand(0, null);
        }
        assertTrue(leader.getHand().get(0) instanceof WindBlast);
        leader.playCardFromHand(0, leader.getEnemyEffectTargetFollowerChoices());

        assertEquals(-5, waterFairy.getDefense());
        assertNull(opponent.getArea()[0]);
    }
}