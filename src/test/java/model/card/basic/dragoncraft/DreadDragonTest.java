package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DreadDragonTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(DreadDragon.class, WaterFairy.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void fanfare() {
        WaterFairy waterFairy = new WaterFairy(opponent);
        opponent.summon(waterFairy);
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, leader.getHand().get(0).getChoices().get(0));

        assertEquals(-3, waterFairy.getDefense());
    }
}