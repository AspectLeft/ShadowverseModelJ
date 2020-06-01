package model.card.basic.swordcraft;

import model.Leader;
import model.card.CardBaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WhiteGeneralTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WhiteGeneral.class, WhiteGeneral.class).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(5);

        final Quickblader quickblader = new Quickblader(leader);
        leader.playCard(quickblader, null);
        final WhiteGeneral whiteGeneral = (WhiteGeneral) leader.getHand().get(0);
        leader.playCardFromHand(0, whiteGeneral.getChoices().get(0));
        assertEquals(3, leader.getAlliedFollowers().get(0).getAttack());
    }
}