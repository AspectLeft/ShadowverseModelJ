package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GhostlyRiderTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(WaterFairy.class, WaterFairy.class).getCurrentLeader();
    }

    @Test
    void lastWords() {
        leader.setMaximumPpAndRecover(7);
        leader.playCardFromHand(0, null);
        GhostlyRider ghostlyRider = new GhostlyRider(leader);
        leader.playCard(ghostlyRider, null);
        ghostlyRider.destroy();
        leader.getGame().settleAllEffects();
        assertTrue(leader.getAlliedFollowers().get(0).hasWard());
    }

}