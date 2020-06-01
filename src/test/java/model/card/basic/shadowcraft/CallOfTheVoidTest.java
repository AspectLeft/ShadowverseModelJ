package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.shadowcraft.token.Lich;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CallOfTheVoidTest {

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(CallOfTheVoid.class, WaterFairy.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void effect() {
        leader.endTurn();
        opponent.playCardFromHand(0, null);
        opponent.endTurn();
        leader.increaseShadow(4);
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, leader.getHand().get(0).getChoices().get(0));

        assertTrue(opponent.getAlliedFollowers().isEmpty());
        assertEquals(1, leader.getCountOfShadow());
        assertNotNull(leader.getArea()[0]);
        assertTrue(leader.getArea()[0] instanceof Lich);
    }
}