package model.card.basic.bloodcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbyssBeastTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(AbyssBeast.class, WaterFairy.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void fanfare() {
        final WaterFairy waterFairy = new WaterFairy(opponent);
        opponent.summon(waterFairy);
        leader.setDefense(10);
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, leader.getHand().get(0).getChoices().get(0));
        assertEquals(-4, waterFairy.getDefense());
    }
}