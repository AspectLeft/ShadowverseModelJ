package model.card.basic.havencraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreaterPriestessTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(GreaterPriestess.class, GreaterPriestess.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        final SummonPegasus summonPegasus = new SummonPegasus(leader);
        final BeastlyVow beastlyVow = new BeastlyVow(leader);
        leader.summon(summonPegasus);
        leader.summon(beastlyVow);
        leader.setMaximumPpAndRecover(5);
        leader.playCardFromHand(0, null);

        assertEquals(3, summonPegasus.getCountDownValue());
        assertEquals(1, beastlyVow.getCountDownValue());
    }
}