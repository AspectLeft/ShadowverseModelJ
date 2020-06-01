package model.card.basic.runecraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InsightTest {

    Game game;

    Leader leader;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fiveIs = Arrays.asList(
                Insight.class, Insight.class, Insight.class, Insight.class, Insight.class);

        game = new Game(1, CardClass.RUNECRAFT, fiveIs,
                2, CardClass.RUNECRAFT, fiveIs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
    }

    @Test
    void draw1() {
        leader.playCardFromHand(0, null);
        assertEquals(4, leader.getHandSize());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Insight);
    }
}