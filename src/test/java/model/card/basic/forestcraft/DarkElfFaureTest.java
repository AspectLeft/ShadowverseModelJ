package model.card.basic.forestcraft;

import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.token.Fairy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DarkElfFaureTest {

    Leader leader;

    @BeforeEach
    void setUp() {
        leader = CardBaseTest.setUpWithDecksOf10(DarkElfFaure.class, DarkElfFaure.class).getCurrentLeader();
    }

    @Test
    void strikeEffect() {
        leader.setMaximumPpAndRecover(3);
        leader.playCardFromHand(0, null);
        leader.endTurn();
        leader.getOpponent().endTurn();
        leader.followerStrike(0, leader.getOpponent());
        assertTrue(leader.getHand().get(4) instanceof Fairy);
    }
}