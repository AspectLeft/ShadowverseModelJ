package model.card.basic.forestcraft;

import model.Game;
import model.Leader;
import model.card.CardBase;
import model.card.CardClass;
import model.card.basic.swordcraft.Quickblader;
import model.report.NoopReporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElfGuardTest {

    Game game;

    Leader leader, opponent;

    @BeforeEach
    void setUp() {
        List<Class<? extends CardBase>> fiveWFs = Collections.nCopies(5, WaterFairy.class);
        List<Class<? extends CardBase>> fiveQs = Collections.nCopies(5, Quickblader.class);

        game = new Game(1, CardClass.FORESTCRAFT, fiveWFs,
                2, CardClass.SWORDCRAFT, fiveQs,
                () -> true, new NoopReporter());
        game.startGame();
        game.changeCardsOnStart(1, Arrays.asList(false, false, false));
        game.changeCardsOnStart(2, Arrays.asList(false, false, false));

        leader = game.getCurrentLeader();
        opponent = game.getLeaderById(2);
    }

    @Test
    void fanfare() {
        final ElfGuard elfGuard = new ElfGuard(leader);
        leader.putCardIntoHand(elfGuard);
        leader.setMaximumPpAndRecover(4);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(0, null);
        leader.playCardFromHand(leader.getHandSize() - 1, null);
        assertSame(leader.getArea()[2], elfGuard);
        assertEquals(2, elfGuard.getAttack());
        assertEquals(4, elfGuard.getDefense());
        assertTrue(leader.isWarded());
        leader.endTurn();

        opponent.playCardFromHand(0, null);
        final List<Integer> targetIndices = opponent.getOpponent().getStrikeTargetIndices();
        assertEquals(1, targetIndices.size());
        assertEquals(2, targetIndices.get(0));
    }
}