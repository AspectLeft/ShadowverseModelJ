package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OkamiTest {
    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(Okami.class, Okami.class).getCurrentLeader();
    }

    @Test
    void selfBuffing() {
        leader.setMaximumPpAndRecover(5);
        leader.playCardFromHand(0, null);
        leader.playCard(new Fairy(leader), null);
        assertEquals(4, leader.getAlliedFollowers().get(0).getAttack());
    }

}