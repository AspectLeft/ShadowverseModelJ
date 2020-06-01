package model.card.basic.forestcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.forestcraft.token.Fairy;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FairyWhispererTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fourFWs = Collections.nCopies(4, FairyWhisperer.class);

        game = new Game(1, CardClass.FORESTCRAFT, fourFWs,
                2, CardClass.FORESTCRAFT, fourFWs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(2);
        leader.playCardFromHand(0, null);
        assertEquals(5, leader.getHandSize());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertTrue(leader.getHand().get(leader.getHandSize() - 2) instanceof Fairy);
    }

}