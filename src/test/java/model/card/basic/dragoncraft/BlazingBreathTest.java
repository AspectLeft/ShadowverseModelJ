package model.card.basic.dragoncraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.forestcraft.token.Fairy;
import model.effect.Choice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlazingBreathTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, BlazingBreath.class);
        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void effect() {
        WaterFairy waterFairy = (WaterFairy) leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.playCardFromHand(0, opponent.getHand().get(0).getChoices().get(0));
        assertEquals(-1, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertNull(leader.getArea()[0]);
    }

    @Test
    void getChoices() {
        leader.playCardFromHand(0, null);
        leader.endTurn();

        final List<List<Choice>> choices = opponent.getHand().get(0).getChoices();
        assertEquals(1, choices.size());
        assertEquals(1, choices.get(0).size());
        assertEquals(0, choices.get(0).get(0).getValue());
    }
}