package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.report.SimpleReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagisteelLionTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10WithReporter(MagisteelLion.class, MagisteelLion.class,
                new SimpleReporter()).getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertEquals(8, leader.getDeckSize());
    }
}