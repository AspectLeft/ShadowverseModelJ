package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.forestcraft.token.Fairy;
import model.card.basic.portalcraft.token.Puppet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PuppeteersStringsTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(PuppeteersStrings.class, WaterFairy.class).getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void effect() {
        opponent.summon(new Fairy(opponent));
        opponent.summon(new Fairy(opponent));

        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);

        assertTrue(opponent.getAlliedFollowers().isEmpty());

        assertTrue(leader.getHand().get(4) instanceof Puppet);
        assertEquals(6, leader.getHandSize());
    }
}