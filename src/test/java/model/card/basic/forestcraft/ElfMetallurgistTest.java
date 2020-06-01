package model.card.basic.forestcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.swordcraft.Quickblader;
import model.effect.Choice;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElfMetallurgistTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fiveWFs = Collections.nCopies(5, WaterFairy.class);
        List<Class<? extends CardBase>> fiveQs = Collections.nCopies(5, Quickblader.class);

        game = new Game(1, CardClass.FORESTCRAFT, fiveWFs,
                2, CardClass.SWORDCRAFT, fiveQs,
                () -> false, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getLeaderById(1);
        opponent = game.getLeaderById(2);
    }

    @Test
    void fanfare() {
        opponent.playCardFromHand(0, null);
        opponent.endTurn();

        final ElfMetallurgist elfMetallurgist = new ElfMetallurgist(leader);
        leader.setMaximumPpAndRecover(4);
        leader.putCardIntoHand(elfMetallurgist);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        final List<Choice> effectTargets = elfMetallurgist.getChoices().get(0);
        assertEquals(1, effectTargets.size());
        assertEquals(0, effectTargets.get(0).getValue());
        leader.playCardFromHand(leader.getHandSize() - 1, effectTargets);
        assertTrue(opponent.getAlliedFollowers().isEmpty());
        assertEquals(1, opponent.getCountOfShadow());
    }
}