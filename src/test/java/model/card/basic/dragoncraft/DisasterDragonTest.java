package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisasterDragonTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, DisasterDragon.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void onStrike() {
        WaterFairy waterFairy = (WaterFairy) leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(5);
        DisasterDragon disasterDragon = (DisasterDragon) opponent.playCardFromHand(0, null);
        opponent.evolve(0, null);
        opponent.followerStrike(0, leader.getAlliedFollowers().get(0));
        assertEquals(-7, waterFairy.getDefense());
        assertEquals(8, disasterDragon.getAttack());
        opponent.endTurn();
        assertEquals(6, disasterDragon.getAttack());
    }
}