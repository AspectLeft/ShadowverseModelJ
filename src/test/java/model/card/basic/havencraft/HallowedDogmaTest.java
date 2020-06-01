package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HallowedDogmaTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(SummonPegasus.class, SummonPegasus.class).getCurrentLeader();
    }

    @Test
    void effect() {
        leader.playCardFromHand(0, null);
        leader.endTurn();
        leader.getOpponent().endTurn();
        HallowedDogma hallowedDogma = new HallowedDogma(leader);
        leader.playCard(hallowedDogma, hallowedDogma.getChoices().get(0));

        assertEquals(1, leader.getAlliedCountdownAmulets().get(0).getCountDownValue());
        assertEquals(5, leader.getHandSize());
    }
}