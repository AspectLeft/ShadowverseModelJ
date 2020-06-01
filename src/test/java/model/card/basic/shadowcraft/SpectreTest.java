package model.card.basic.shadowcraft;

import model.Game;
import model.Leader;
import model.card.CardBaseTest;
import model.card.basic.forestcraft.ElfGuard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SpectreTest {
    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        Game game = CardBaseTest.setUpWithDecksOf10(Spectre.class, ElfGuard.class);
        leader = game.getCurrentLeader();
        opponent = leader.getOpponent();
    }

    @Test
    void bane() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        leader.endTurn();

        opponent.setMaximumPpAndRecover(2);
        opponent.playCardFromHand(0, null);
        opponent.endTurn();

        leader.followerStrike(0, opponent.getArea()[0]);

        assertEquals(1, opponent.getCountOfShadow());
        assertNull(opponent.getArea()[0]);
    }

}