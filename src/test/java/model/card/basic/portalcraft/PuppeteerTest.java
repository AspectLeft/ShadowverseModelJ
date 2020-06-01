package model.card.basic.portalcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.Follower;
import model.card.basic.portalcraft.token.Puppet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PuppeteerTest {
    Leader leader;
    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Puppeteer.class, Puppeteer.class).getCurrentLeader();
    }

    @Test
    void evolve() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        leader.evolve(0, null);
        Follower puppet = (Follower)leader.getHand().get(leader.getHandSize() - 1);
        assertTrue(puppet instanceof Puppet);
        assertTrue(puppet.hasBane());
    }

}