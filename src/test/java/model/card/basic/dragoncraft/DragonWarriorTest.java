package model.card.basic.dragoncraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DragonWarriorTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, DragonWarrior.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void evolve() {
        Follower follower = (Follower) leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(4);
        DragonWarrior dragonWarrior = (DragonWarrior) opponent.playCardFromHand(0, null);
        opponent.evolve(0, dragonWarrior.getEvolveChoices().get(0));
        assertTrue(leader.getAlliedFollowers().isEmpty());
        assertEquals(-2, follower.getDefense());
    }
}