package model.card.basic.shadowcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayfulNecromancerTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(PlayfulNecromancer.class, Fairy.class).getCurrentLeader();
    }

    @Test
    void evolve() {
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);
        leader.evolve(0, null);

        assertEquals(3, leader.getAlliedFollowers().size());
    }
}