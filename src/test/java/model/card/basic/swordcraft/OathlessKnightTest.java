package model.card.basic.swordcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.swordcraft.token.Knight;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OathlessKnightTest {

    Game game;

    Leader leader;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> tenOKs = Collections.nCopies(10, OathlessKnight.class);

        game = new Game(1, CardClass.SWORDCRAFT, tenOKs,
                2, CardClass.SWORDCRAFT, tenOKs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
    }

    @Test
    void fanfare() {
        leader.setMaximumPpAndRecover(6);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        assertTrue(leader.getArea()[4] instanceof OathlessKnight);
        assertTrue(leader.getArea()[1] instanceof Knight);
        assertTrue(leader.getArea()[3] instanceof Knight);
        assertEquals(0, leader.getCountOfShadow());
    }
}