package model.card.basic.runecraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.forestcraft.WaterFairy;
import model.card.basic.runecraft.token.ClayGolem;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConjureGolemTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        final List<Class<? extends CardBase>> fourWFs = Arrays.asList(
                WaterFairy.class, WaterFairy.class, WaterFairy.class, WaterFairy.class);

        final List<Class<? extends CardBase>> tenCGs = Collections.nCopies(10, ConjureGolem.class);

        game = new Game(1, CardClass.FORESTCRAFT, fourWFs,
                2, CardClass.RUNECRAFT, tenCGs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void effect() {
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        opponent.playCardFromHand(0, null);
        assertEquals(1, opponent.getCountOfShadow());
        assertTrue(opponent.getArea()[0] instanceof ClayGolem);
    }
}