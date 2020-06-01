package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoseGardenerTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, RoseGardener.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void evolve() {
        leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(4);
        RoseGardener roseGardener = (RoseGardener) opponent.playCardFromHand(0, null);
        opponent.evolve(0, roseGardener.getEvolveChoices().get(0));
        assertEquals(0, leader.getAlliedFollowers().size());
        assertEquals(4, leader.getHandSize());
    }

}