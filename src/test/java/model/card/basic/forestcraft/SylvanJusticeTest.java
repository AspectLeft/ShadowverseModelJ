package model.card.basic.forestcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.forestcraft.token.Fairy;
import model.effect.Choice;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SylvanJusticeTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        final List<Class<? extends CardBase>> fourWFs = Arrays.asList(
                WaterFairy.class, WaterFairy.class, WaterFairy.class, WaterFairy.class);

        final List<Class<? extends CardBase>> fiveSJs = Collections.nCopies(5, SylvanJustice.class);

        game = new Game(1, CardClass.FORESTCRAFT, fourWFs,
                2, CardClass.FORESTCRAFT, fiveSJs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void effect() {
        WaterFairy waterFairy = (WaterFairy) leader.playCardFromHand(0, null);
        leader.endTurn();
        opponent.setMaximumPpAndRecover(2);
        opponent.playCardFromHand(0, opponent.getHand().get(0).getChoices().get(0));
        assertEquals(-1, waterFairy.getDefense());
        assertEquals(1, leader.getCountOfShadow());
        assertTrue(leader.getHand().get(leader.getHandSize() - 1) instanceof Fairy);
        assertNull(leader.getArea()[0]);
        assertTrue(opponent.getHand().get(opponent.getHandSize() - 1) instanceof Fairy);
    }

    @Test
    void getChoices() {
        leader.playCardFromHand(0, null);
        leader.endTurn();

        final List<List<Choice>> choices = opponent.getHand().get(0).getChoices();
        assertEquals(1, choices.size());
        assertEquals(1, choices.get(0).size());
        assertEquals(0, choices.get(0).get(0).getValue());
    }
}