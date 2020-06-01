package model.card.basic.runecraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightningShooterTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Insight.class, WaterFairy.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void fanfare() {
        leader.endTurn();
        WaterFairy waterFairy = (WaterFairy) opponent.playCardFromHand(0, null);
        opponent.endTurn();

        leader.setMaximumPpAndRecover(10);
        LightningShooter lightningShooter = new LightningShooter(leader);
        leader.putCardIntoHand(lightningShooter);

        for (int i = 0; i < 5; ++i) {
            leader.playCardFromHand(0, null);
        }
        assertTrue(leader.getHand().get(0) instanceof LightningShooter);
        leader.playCardFromHand(0, lightningShooter.getChoices().get(0));

        assertEquals(-5, waterFairy.getDefense());
        assertNull(opponent.getArea()[0]);
    }
}