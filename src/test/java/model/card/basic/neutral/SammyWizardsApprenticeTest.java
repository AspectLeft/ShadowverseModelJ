package model.card.basic.neutral;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SammyWizardsApprenticeTest {
    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> tenSWAs = Collections.nCopies(10, SammyWizardsApprentice.class);

        game = new Game(1, CardClass.SWORDCRAFT, tenSWAs,
                2, CardClass.SWORDCRAFT, tenSWAs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(2);

        assertEquals(4, leader.getHandSize());
        assertEquals(3, opponent.getHandSize());
        leader.playCardFromHand(0, null);
        assertEquals(4, leader.getHandSize());
        assertEquals(4, opponent.getHandSize());
    }
}